package org.pro.springorder;

import org.pro.springorder.order.OrderItem;
import org.pro.springorder.order.OrderProperties;
import org.pro.springorder.order.OrderService;
import org.pro.springorder.voucher.FixedAmountVoucher;
import org.pro.springorder.voucher.VoucherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.UUID;

public class OrderTester {

    private static final Logger logger = LoggerFactory.getLogger(OrderTester.class);

    public static void main(String[] args) throws IOException {
        AnsiOutput.setEnabled(AnsiOutput.Enabled.ALWAYS);
        var applicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);

//        var environment = applicationContext.getEnvironment();
//        var version = environment.getProperty("example.version");
//        var minimumOrderAmount = environment.getProperty("example.minimum-order-amount");
//        var supportVendors = environment.getProperty("example.support-vendors", List.class);
//        var description = environment.getProperty("example.description", List.class);
//        System.out.println(MessageFormat.format("version = {0}", version));
//        System.out.println(MessageFormat.format("minimumOrderAmount = {0}", minimumOrderAmount));
//        System.out.println(MessageFormat.format("supportVendors = {0}", supportVendors));
//        System.out.println(MessageFormat.format("description = {0}", description));
        var orderProperties = applicationContext.getBean(OrderProperties.class);
        logger.warn("logger name => {}" + logger.getName());
        logger.warn("version = {}", orderProperties.getVersion());
        logger.warn("minimumOrderAmount = {}", orderProperties.getMinimumOrderAmount());
        logger.warn("supportVendors = {}", orderProperties.getSupportVendors());
        logger.warn("description = {}", orderProperties.getDescription());

//        var resource = applicationContext.getResource("classpath:application.yaml");
//        var resource2 = applicationContext.getResource("file:test/sample.txt");
//        var resource3 = applicationContext.getResource("https://stackoverflow.com/");
//        System.out.println(MessageFormat.format("Resource -> '{'0'}'{0}", resource3.getClass().getCanonicalName()));
//        var strings = Files.readAllLines(resource.getFile().toPath());
//        System.out.println(strings.stream().reduce("", (a, b) -> a + "\n" + b));
//        var readableByteChannel = Channels.newChannel(resource3.getURL().openStream());
//        var bufferedReader = new BufferedReader(Channels.newReader(readableByteChannel, StandardCharsets.UTF_8));
//        var contents = bufferedReader.lines().collect(Collectors.joining("\n"));
//        System.out.println(contents);



        var customerId = UUID.randomUUID();
//        var voucherRepository = BeanFactoryAnnotationUtils.qualifiedBeanOfType(applicationContext.getBeanFactory(), VoucherRepository.class, "local");
        var voucherRepository = applicationContext.getBean(VoucherRepository.class);
        var voucher = voucherRepository.insert(new FixedAmountVoucher(UUID.randomUUID(), 10L));
//
//        System.out.println(MessageFormat.format("is Jdbc Repo -> {0}", voucherRepository instanceof JdbcVoucherRepository));
//        System.out.println(MessageFormat.format("is Jdbc Repo -> {0}", voucherRepository.getClass().getCanonicalName()));

        var orderService = applicationContext.getBean(OrderService.class);
        var order = orderService.createOrder(customerId, new ArrayList<OrderItem>() {{
            add(new OrderItem(UUID.randomUUID(), 100L, 1));
        }}, voucher.getVoucherId());
        Assert.isTrue(
                order.totalAmount() == 90L,
                MessageFormat.format("totalAmount {0} is not 90", order.totalAmount()));

        applicationContext.close();
    }
}
