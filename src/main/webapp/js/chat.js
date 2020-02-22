$(document).ready(function(){
    let userName = sessionStorage.getItem("userName");
    let roomName = sessionStorage.getItem("roomName");
    let divListMessage = $('#showMessageChat');
    let idMess = 0;
    let isBtnSubmitMessageClicked = false;
    let nbMessInRoom1 = 0;
    let nbMessInRoom2 = 0

    setInterval(function(){
        numberMessInRoom();
        if(nbMessInRoom1 != nbMessInRoom2){

            showMessage(roomName);
        }
    }, 5000);

   $('#btnBackToPageUtilisateur').on('click', function(e){
       e.preventDefault();
       sessionStorage.removeItem("roomName");
       $(location).attr('href', 'pageUtilisateur.html');
   });

   $('#btnSubmitMessage').on('click', function(e){
      e.preventDefault();
      isBtnSubmitMessageClicked = true;
      let messVal = $('#messageUser').val();
      let data = {
          "user": {
              "name": userName
          },
          "message": messVal
      };
      console.log("Data: " + JSON.stringify(data));
      $.ajax({
          url: 'dispatcher/backOffice/ressourceRoom/addMessageToRoom',
          type: 'POST',
          data: {room: roomName, message: JSON.stringify(data)},
          success: function(data, textStatus, xhr){
              if(xhr.statusCode().status === 200){
                  console.log('Send message successful!!!');
                  numberMessInRoom();
                  showMessage(roomName);
                  $('#messageUser').val("");
              }
          },
          error: function(error){
              console.error(error);
          }
      });
   });

   function showMessage(info){
       console.log('Id Mess in SHOW MESS before AJAX: ' + idMess);
       if(!isBtnSubmitMessageClicked){
           idMess = nbMessInRoom1;
       }
       else{
           idMess = nbMessInRoom2;
       }
       $.ajax({
           url: 'dispatcher/backOffice/ressourceRoom/listMessBeginWith.json',
           type: 'GET',
           data: {room: info, idMess: idMess},
           success: function(data, textStatus, xhr){
               if(xhr.statusCode().status === 200 && data.messages != undefined){
                   if((isBtnSubmitMessageClicked) ||
                       (!isBtnSubmitMessageClicked && idMess <= nbMessInRoom1)){
                       for(let i = 0; i < data.messages.messages.length; i++){
                           console.log('LENGTH ARRAY MESS CURRENT: '+data.messages.messages.length);
                           let user = data.messages.messages[i].user.name;
                           let mess = data.messages.messages[i].message;

                           divListMessage.append($('<li class="left clearfix"><span class="chat-img pull-left"><img src="http://placehold.it/50/55C1E7/fff&text=U" alt="User Avatar" class="img-circle" /></span><div class="chat-body clearfix"><div class="header"><strong class="primary-font">' + user + '</strong> <small class="pull-right text-muted"><span class="glyphicon glyphicon-time"></span>1 mins ago</small> </div><p>\n' + mess + '</p></div></li>'));

                       }
                       nbMessInRoom1 = nbMessInRoom2;
                       console.log('Id Mess in SHOW MESS after: ' + idMess);
                   }
                   isBtnSubmitMessageClicked = false;
               }
           },
           error: function(error){
               console.error(error);
           }
       });
   }

   function numberMessInRoom(){
       $.ajax({
           url: 'dispatcher/backOffice/ressourceRoom/numberMessageOfRoom.json',
           type: 'GET',
           data: {room: roomName},
           success: function(data, textStatus, xhr){
               if(xhr.statusCode().status === 200){
                   nbMessInRoom2 = data.numberMessage;
               }
           },
           error: function(error){
               console.error(error);
           }
       });
   }
});