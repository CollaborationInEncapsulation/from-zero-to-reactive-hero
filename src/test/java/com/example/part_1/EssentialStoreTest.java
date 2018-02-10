package com.example.part_1;

import com.example.part_1.part1_extra_store_optional.Order;
import com.example.part_1.part1_extra_store_optional.Product;
import com.example.part_1.part1_extra_store_optional.ProductsCatalog;
import com.example.part_1.part1_extra_store_optional.UserActivityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import rx.Observable;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ProductsCatalog.class)
public class EssentialStoreTest {

    @Test
    public void retrievingTotalPriceTest() {
        PowerMockito.mockStatic(ProductsCatalog.class);
        PowerMockito.when(ProductsCatalog.findById(any()))
                .then(a -> new Product(a.getArgument(0), a.getArgument(0), 100));
        List<String> productsIds = Arrays.asList("123", "321", "1235", "1312");

        new Order("test", "test", productsIds)
                .getTotalPrice()
                .test()
                .assertValue(400L)
                .awaitTerminalEvent()
                .assertCompleted();
    }

    @Test
    public void findUsersMostExpensivePurchaseTest() {
        PowerMockito.mockStatic(ProductsCatalog.class);
        PowerMockito.when(ProductsCatalog.findById(any()))
                .then(a -> new Product(a.getArgument(0), a.getArgument(0), Long.parseLong(a.getArgument(0))));

        UserActivityUtils
                .findMostExpansivePurchase(Observable.just(
                        new Order("1", "test", Arrays.asList("1", "2", "3")),
                        new Order("2", "test", Arrays.asList("4", "5")),
                        new Order("3", "test", Arrays.asList("6"))
                ))
                .test()
                .assertValue(new Product("6", "6", 6))
                .awaitTerminalEvent()
                .assertCompleted();
    }
}
