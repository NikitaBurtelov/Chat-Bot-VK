package connect;

import com.petersamokhin.bots.sdk.clients.Group;
import com.petersamokhin.bots.sdk.objects.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Connect {
    private static final int idGroup = 195134131;
    private static final String token = "d9d8dd3f78413d15d0e443763f56d1ca1983196de290317b109e06e12565a1779e6224f3b72f612da5c1d";

    public static void main(String[] args) {
        Group group = new Group(idGroup, token);

        group.onSimpleTextMessage(message ->
                new Message()
                    .from(group)
                    .to(message.authorId())
                    .text("Пиздуй ботать, а не трепаться !!!!!")
                    .photo("target\\classes\\picture_2.jpg")
                    .send()
        );

        group.onPhotoMessage(message ->
                new Message()
                .from(group)
                .to(message.authorId())
                .text("Ну и зачем мне твои нужны твои картинки ???")
                .send()
        );

        group.onStickerMessage(message ->
                new Message()
                        .from(group)
                        .to(message.authorId())
                        .text("Иииии ????")
                        .send()
        );

    }
}