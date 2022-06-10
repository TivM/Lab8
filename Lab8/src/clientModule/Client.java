package clientModule;

import common.utility.Request;
import common.utility.Response;
import common.utility.User;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Scanner;
import java.util.Set;

public class Client {
    private String host;
    private int port;
    private User user;
    private DatagramSocket socket;
    private SocketAddress address;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Client(String host, int port){
        this.host = host;
        this.port = port;
        try {
            Scanner scanner = new Scanner(System.in);
            socket = new DatagramSocket(scanner.nextInt());
            address = new InetSocketAddress(this.host, this.port);
        } catch (IOException exception) {
            System.out.println("Произошла ошибка при работе с сервером!");
            exception.printStackTrace();
            System.exit(0);
        }
    }

    public Response receive() throws IOException, ClassNotFoundException {
        byte[] getBuffer = new byte[socket.getReceiveBufferSize()];
        DatagramPacket getPacket = new DatagramPacket(getBuffer, getBuffer.length);
        socket.receive(getPacket);
        return deserialize(getPacket);
    }

    private Response deserialize(DatagramPacket getPacket) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(getPacket.getData());
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        Response response = (Response) objectInputStream.readObject();
        byteArrayInputStream.close();
        objectInputStream.close();
        return response;
    }

    public void send(Request request) throws IOException {
        byte[] sendBuffer = serialize(request);
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address);
        socket.send(sendPacket);
    }

    private byte[] serialize(Request request) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(request);
        byte[] buffer = byteArrayOutputStream.toByteArray();
        objectOutputStream.flush();
        byteArrayOutputStream.flush();
        byteArrayOutputStream.close();
        objectOutputStream.close();
        return buffer;
    }
}
