$(document).ready(function(){
   let userName = sessionStorage.getItem("userName");
   bodyLoad();
   setInterval(function(){
       bodyLoad();
   }, 5000);

   $('#btnSubmit').on('click', function(e){
       e.preventDefault();
       let inputVal = $('#roomName').val();
       createRoom(inputVal);
   });

   $('#btnDisconnect').on('click', function(e){
       e.preventDefault();
       sessionStorage.removeItem("userName");
       $(location).attr('href', 'pageAccueil.html');
   });

   function goToRoom(e, element){
       e.preventDefault();
       let roomName = $(element).find( "h4" ).attr('id');

       console.log(roomName);
       sessionStorage.setItem("roomName", roomName);
       $.ajax({
           url: 'dispatcher/backOffice/ressourceRoom/addUserToRoom',
           type: 'POST',
           data: {roomName: roomName, user: userName},
           success: function(data, textStatus, xhr){
               if(xhr.statusCode().status === 200){
                   $(location).attr('href', 'pageSalon.html');
               }
           },
           error: function(error){
               console.error(error);
           }
       });
   }

   function createRoom(roomName){
       $.ajax({
           url: 'dispatcher/backOffice/ressourceRoom/addRoom',
           type: 'POST',
           data: {roomName: roomName},
           success: function(data, textStatus, xhr){
               if(xhr.statusCode().status === 200){
                   bodyLoad();
                   $('#roomName').val("");
               }
           },
           error: function(error){
               console.error(error);
           }
       });
   }

   function bodyLoad(){
       console.log('Body Load!!!');
       $("h4#userName").html("User: "+userName);
       $.ajax({
           url: 'dispatcher/backOffice/ressourceRoom/allRooms.json',
           type: 'GET',
           data: {user: userName},
           success: function(data){
               let ul = $('#listRoom');
               ul.empty();
               for(let i = 0; i < data.listRoom.rooms.length; i++){
                   let div = $('<div class="col-sm-4"></div>')
                   let li = $('<div class="team-member"><img class="mx-auto rounded-circle" src="img/team/1.jpg" alt=""><h4 id="'+data.listRoom.rooms[i].name+'">'+data.listRoom.rooms[i].name+'</h4></div>');
                   let btnGoToRoom = $('<button class="btn btn-default" id="btnGoToRoom">Go to room</button>');
                   btnGoToRoom.on('click', function(e){
                       let that = li;
                       goToRoom(e, that);
                   });


                   li.append(btnGoToRoom);
                   div.append(li);
                   ul.append(div);
               }
           },
           error: function(error){
               console.error(error);
           }
       });
   }
});