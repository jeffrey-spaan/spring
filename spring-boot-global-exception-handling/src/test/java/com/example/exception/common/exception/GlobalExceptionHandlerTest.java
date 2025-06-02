package com.example.exception.common.exception;

import com.example.exception.common.i18n.I18nService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ProblemDetail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class GlobalExceptionHandlerTest {

    private static final String ERROR_PATH = "https://example.com/error";
    private static final String ERROR_MESSAGE = "i18n.test.message.no.args";
    private static final String I18N_MESSAGE = "This is a test message without arguments";
    private static final int HTTP_STATUS_NOT_FOUND = 404;

    @Mock
    private I18nService i18nService;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler.setErrorPath(ERROR_PATH);
    }

    @Test
    void testHandleResourceNotFoundException() {
        ResourceNotFoundExceptionWithI18n ex = new ResourceNotFoundExceptionWithI18n(ERROR_MESSAGE, null);
        when(i18nService.getMessage(ERROR_MESSAGE, null)).thenReturn(I18N_MESSAGE);

        ProblemDetail pd = globalExceptionHandler.handleResourceNotFoundExceptionWithI18n(ex);

        assertEquals(HTTP_STATUS_NOT_FOUND, pd.getStatus());
        assertEquals(I18N_MESSAGE, pd.getDetail());
        assertEquals(ERROR_PATH, pd.getType().toString());
    }
}