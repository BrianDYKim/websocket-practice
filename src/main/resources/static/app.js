const socket = new SockJS('/ws');
const stompClient = Stomp.over(socket);

stompClient.connect({}, (frame) => {
    console.log('Connected: ' + frame);
    const userId = "user123"; // 사용자 ID
    stompClient.subscribe('/queue/' + userId, (message) => {
        console.log('Received message: ' + message.body);
    });
});

function sendMessage(userId, messageContent) {
    const message = {
        userId: userId,
        content: messageContent
    };
    stompClient.send("/app/send", {}, JSON.stringify(message));
}