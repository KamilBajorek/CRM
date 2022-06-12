package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.form.PurchaseCreateForm;
import com.bajorek_kalandyk.crm.domain.model.Client;
import com.bajorek_kalandyk.crm.domain.model.Product;
import com.bajorek_kalandyk.crm.domain.model.Purchase;
import com.bajorek_kalandyk.crm.repository.ClientRepository;
import com.bajorek_kalandyk.crm.repository.ProductRepository;
import com.bajorek_kalandyk.crm.repository.PurchaseRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static java.sql.Timestamp.valueOf;
import static java.time.LocalDateTime.now;
import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseServiceImplTest
{
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ClientRepository clientRepository;

    @Mock
    private PurchaseRepository repository;

    @InjectMocks
    private PurchaseServiceImpl purchaseService;

    private static final Client TEST_CLIENT_1 = Client.builder().id(1L).name("Client").build();
    private static final Product TEST_PRODUCT_1 = Product.builder().id(2L).name("Product").build();

    @Before
    public void setUp()
    {
        when(clientRepository.findById(1L)).thenReturn(ofNullable(TEST_CLIENT_1));
        when(productRepository.findById(1L)).thenReturn(ofNullable((TEST_PRODUCT_1)));
        when(repository.save(any(Purchase.class)))
                .thenAnswer(i -> i.getArguments()[0]);
    }

    @Test(expected = Exception.class)
    public void throwExceptionWhenCreatingPurchaseWithNonExistingClient() throws Exception
    {
        // given
        final PurchaseCreateForm createForm = PurchaseCreateForm.builder()
                .clientId(2L)
                .productId(1L)
                .build();

        // when
        final Purchase result = purchaseService.createPurchase(createForm);
    }

    @Test(expected = Exception.class)
    public void throwExceptionWhenCreatingPurchaseWithNonExistingProduct() throws Exception
    {
        // given
        final PurchaseCreateForm createForm = PurchaseCreateForm.builder()
                .clientId(1L)
                .productId(2L)
                .build();

        // when
        final Purchase result = purchaseService.createPurchase(createForm);
    }


    @Test
    public void createPurchaseWhenFormDataIsCorrect() throws Exception
    {
        // given
        final PurchaseCreateForm createForm = PurchaseCreateForm.builder()
                .clientId(1L)
                .productId(1L)
                .build();

        // when
        final Purchase result = purchaseService.createPurchase(createForm);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getDate()).isBetween(valueOf(now().minusMinutes(1)),
                valueOf(now().plusMinutes(1)));
        assertThat(result.getClientId()).isEqualTo(1L);
        assertThat(result.getProduct()).isEqualTo(TEST_PRODUCT_1);
    }
}