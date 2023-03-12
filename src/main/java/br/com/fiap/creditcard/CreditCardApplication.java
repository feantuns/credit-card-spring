package br.com.fiap.creditcard;

import br.com.fiap.creditcard.batch.ItemSkipPolicy;
import br.com.fiap.creditcard.batch.JobCompletionListener;
import br.com.fiap.creditcard.dto.StudentBatchIn;
import br.com.fiap.creditcard.dto.StudentBatchOut;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.core.io.PathResource;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
@EnableBatchProcessing
public class CreditCardApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditCardApplication.class, args);
	}


	@Value("${fiap.creditcard.resource}")
	private String filePath;

	@Bean
	public ItemReader<StudentBatchIn> itemReader(){
		return new FlatFileItemReaderBuilder<StudentBatchIn>()
				.name("file reader")
				.resource(new PathResource(filePath))
				.delimited().names("line")
				.targetType(StudentBatchIn.class)
				.build();
	}

	@Bean
	public ItemProcessor<StudentBatchIn, StudentBatchOut> itemProcessor(){
		return studentIn -> {
			String line = studentIn.getLine();

			if (line == null || line.trim().length() == 0) {
				return null;
			}

			if (line.contains("----")) {
				return null;
			}

			String rm = line.substring(line.length() - 14);
			String name = line.replace(rm, "").trim();

			StudentBatchOut out = new StudentBatchOut();
			out.setName(name);
			out.setRm(rm);
			return out;
		};
	}

	@Bean
	public ItemWriter<StudentBatchOut> itemWriter(DataSource dataSource){
		return new JdbcBatchItemWriterBuilder<StudentBatchOut>()
				.dataSource(dataSource)
				.beanMapped()
				.sql("insert into TB_STUDENT(name, rm) values (:name, :rm)")
				.build();
	}

	@Bean
	public Step step(
			StepBuilderFactory stepBuilderFactory,
			ItemReader<StudentBatchIn> itemReader,
			ItemProcessor<StudentBatchIn, StudentBatchOut> itemProcessor,
			ItemWriter<StudentBatchOut> itemWriter
	){
		return stepBuilderFactory.get("student process step")
				.<StudentBatchIn, StudentBatchOut>chunk(1000)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.faultTolerant()
				.skipPolicy(new ItemSkipPolicy())
				.skip(Exception.class)
				.allowStartIfComplete(true)
				.build();
	}

	@Bean
	public JobCompletionListener jobCompletionListener() {
		return new JobCompletionListener();
	}

	@Bean
	public Job job(JobBuilderFactory jobBuilderFactory,
				   Step step){
		return jobBuilderFactory.get("student import job")
				.start(step)
				.listener(jobCompletionListener())
				.build();
	}

}
