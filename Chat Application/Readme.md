## Chat Application (Client-Server)

### Description

This project implements a simple client-server chat application using Java Swing for the graphical user interface and standard Java networking for communication. It allows real-time message exchange between a single client and a server, with the client application also logging chat history to a timestamped text file for record-keeping.

### Features

  * **Real-time Messaging:** Allows a client and a server to exchange messages in real-time.
  * **GUI Interface:** Both the client and server have a user-friendly graphical interface for sending and receiving messages.
  * **Chat History Logging (Client-side):** The client application logs all chat messages (both sent and received) to a timestamped text file for later review.
  * **Connection Handling:** Manages connection establishment and termination between the client and server.
  * **"End Chat" Functionality:** The client can initiate the termination of the chat session using an "End Chat" button or by typing "bye".

### How to Run

To run this application, you will need a Java Development Kit (JDK) installed on your system.

#### 1\. Compile the Source Code

Open a terminal or command prompt, navigate to the directory where you saved `ChatServerX.java` and `ChatClientX.java`, and compile them:

```bash
javac ChatServerX.java ChatClientX.java
```

#### 2\. Start the Server

Run the server application first. In your terminal, execute:

```bash
java ChatServerX
```

A "Chat Server" window will appear, indicating that the server has started and is waiting for a client to connect.

#### 3\. Start the Client

In a *separate* terminal or command prompt, run the client application:

```bash
java ChatClientX
```

A "Chat Client" window will appear and attempt to connect to the server. Once connected, both the server and client windows will display a "Client connected\!" and "Connected to server." message respectively.

### Usage

1.  **Sending Messages:**

      * Type your message into the text field at the bottom of either the "Chat Server" or "Chat Client" window.
      * Press the "Send" button or hit Enter to send the message.
      * The message will appear in your chat area and be transmitted to the other party.

2.  **Ending the Chat (Client-side):**

      * The client can end the chat session by clicking the "End Chat" button.
      * Alternatively, the client can type "bye" (case-insensitive) into the input field and send it.
      * Upon ending the chat, the client application will close.

3.  **Chat Logs (Client-side):**

      * A log file named `ChatHistory_Client_YYYYMMDD_HHmmss.txt` (where `YYYYMMDD_HHmmss` is the timestamp of when the client started) will be created in the same directory where you run the client.
      * This file will contain a timestamped record of all messages exchanged during the client's session.

### Technical Details

  * **Server (`ChatServerX.java`):**

      * Listens for incoming client connections on port `12345`.
      * Uses `ServerSocket` and `Socket` for network communication.
      * `PrintWriter` and `BufferedReader` are used for sending and receiving text messages.
      * The server's GUI updates to show client messages.

  * **Client (`ChatClientX.java`):**

      * Connects to the server at `localhost` on port `12345`.
      * Uses `Socket` for network communication.
      * `PrintWriter` and `BufferedReader` are used for sending and receiving text messages.
      * Includes a logging mechanism to save chat history to a file.
      * Handles closing connections gracefully.

### Limitations

  * **Single Client:** The current server implementation can only handle one client connection at a time.
  * **Basic UI:** The user interface is functional but basic.
  * **No Error Handling for Server Disconnection:** If the server disconnects unexpectedly, the client might not handle it gracefully beyond a "Connection closed" message.
  * **Localhost Only:** The client is hardcoded to connect to `localhost`. To connect to a server on a different machine, you would need to modify the `ChatClientX.java` file to specify the server's IP address.

### Future Enhancements

  * **Multi-client Support:** Implement multithreading on the server to handle multiple concurrent client connections.
  * **Improved UI:** Enhance the user interface with better styling, emoticons, etc.
  * **Usernames:** Allow users to set usernames for identification in the chat.
  * **Private Messaging:** Add functionality for private messages between specific users in a multi-client setup.
  * **Robust Error Handling:** Improve error handling for network disconnections and other exceptions.
  * **File Transfer:** Add capabilities to send files between client and server.