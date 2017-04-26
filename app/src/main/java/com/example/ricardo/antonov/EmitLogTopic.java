package com.example.ricardo.antonov;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

/**
 * Created by ricardo on 26/04/2017.
 */

public abstract class EmitLogTopic {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void emit(String msg, String ip, String chave){


        //public static void main(String[] argv) {
            Connection connection = null;
            Channel channel = null;
            try {
                ConnectionFactory factory = new ConnectionFactory();
                factory.setHost(ip);

                connection = factory.newConnection();
                channel = connection.createChannel();

                channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

                String routingKey = chave;
                String message = msg;

                channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");

            }
            catch  (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (connection != null) {
                    try {
                        connection.close();
                    }
                    catch (Exception ignore) {}
                }
            }
        //}

    }

        /*private static String getRouting(String[] strings){
            if (strings.length < 1)
                return "anonymous.info";
            return strings[0];
        }

        private static String getMessage(String[] strings){
            if (strings.length < 2)
                return "Hello World!";
            return joinStrings(strings, " ", 1);
        }

        private static String joinStrings(String[] strings, String delimiter, int startIndex) {
            int length = strings.length;
            if (length == 0 ) return "";
            if (length < startIndex ) return "";
            StringBuilder words = new StringBuilder(strings[startIndex]);
            for (int i = startIndex + 1; i < length; i++) {
                words.append(delimiter).append(strings[i]);
            }
            return words.toString();
        }*/

}
