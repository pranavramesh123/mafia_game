package co.mafiagame.bot;

import co.mafiagame.bot.telegram.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author Esa Hekmatizadeh
 */
@Component
public class TelegramClient {
    private static final Logger logger = LoggerFactory.getLogger(TelegramClient.class);

    @Value("${mafia.telegram.api.url}")
    private String telegramUrl;
    @Value("${mafia.telegram.token}")
    private String telegramToken;
    private RestTemplate restTemplate = new RestTemplate();
    private ObjectMapper mapper;

    public SendMessageResult send(SendMessage message) {
        try {
            logger.info("sending message: {}", message);
            return restTemplate.postForEntity(telegramUrl + telegramToken + "/sendMessage", message, SendMessageResult.class).getBody();
        } catch (HttpClientErrorException e) {
            logger.error("error sending message {}: {}", message, e.getResponseBodyAsString(), e);
            try {
                return mapper.readValue(e.getResponseBodyAsByteArray(), SendMessageResult.class);
            } catch (IOException e1) {
                logger.error("could not read json value of SendMessageResult form {}", e.getResponseBodyAsString());
                e1.printStackTrace();
            }
        }
        return null;
    }

    public TMessage editMessageReplyMarkup(EditMessageReplyMarkupRequest request) {
        try {
            return restTemplate.postForEntity(telegramUrl + telegramToken +
                    "/editMessageReplyMarkup", request, TMessage.class).getBody();
        } catch (Exception e) {
            logger.error("error updating message reply markup {}", request, e);
        }
        return null;
    }

    public TMessage editMessageText(EditMessageTextRequest request) {
        try {
            return restTemplate.postForEntity(telegramUrl + telegramToken + "/editMessageText",
                    request, TMessage.class).getBody();
        } catch (Exception e) {
            logger.error("error updating message " + request, e);
        }
        return null;
    }

}
