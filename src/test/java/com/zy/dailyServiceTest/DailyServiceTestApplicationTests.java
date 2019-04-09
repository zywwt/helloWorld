package com.zy.dailyServiceTest;

import com.zy.dailyServiceTest.common.BufferedReaderProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DailyServiceTestApplicationTests {

	@Test
	public void contextLoads() {
		test00(() -> System.out.println());

	}

	public void test00(Runnable runnable) {
		runnable.run();
	}

	public void test01() throws IOException{
		processFile(new BufferedReaderProcessor() {
			@Override
			public String process(BufferedReader br) throws IOException {
				return br.readLine();
			}
		});

		processFile((BufferedReader br) -> br.readLine());
	}

	/**
	 *	java 7  特性 try（ 在此处创建的 流式 对象不用手动关闭 ）{}
	 */
	public static String processFile(BufferedReaderProcessor brp) throws IOException{
		try(
				BufferedReader br = new BufferedReader(new FileReader("date.txt"))
				) {
			return brp.process(br);
		}
	}

	@Test
	public void test02(){
		System.out.println(test((String s) -> s.equals("1"),"1"));
	}

	public static boolean test(Predicate<String> p,String str) {
		return p.test(str);
	}

	@Test
	public void test04() {
		forEach(Arrays.asList("12","23","34","45"),(String str) -> System.out.println(str.substring(0,1)));
	}

	public static <T> void forEach(List<T> list, Consumer<T> c) {
		for(T t : list) {
			c.accept(t);
		}
	}

	@Test
	public void test05() {
		compare(Arrays.asList("apple","prie","banana"),(String str) -> {String l = str.length() + "";
			return l;});
	}

	public static <T,R> List<R> compare(List<T> list,Function<T,R> fun) {
		List<R> l = new ArrayList<>();
		for(T t :list) {
			l.add(fun.apply(t));
		}
		return l;
	}


}



