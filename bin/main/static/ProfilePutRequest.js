(function loadProfiles(){

<<<<<<< HEAD
    fetch('profileInfo')
=======
    fetch('http://localhost:8080/profileInfo')
>>>>>>> track-books
        .then(function(response) {
            return response.json();
        })
        .then(function(myJson) {
            console.log(JSON.stringify(myJson));
        });
<<<<<<< HEAD
=======

>>>>>>> track-books
})();

(function(){

    var userName = 'Shane';
    var bio = 'PLEASE WORK OMG';
    var url = `/updateBio?name=${userName}&bio=${bio}`;

    //Update Bio Promise to wait for response
    putBio(url)
        .then(res => {
            //update FirstName Area Needs Done
            console.log(res);
<<<<<<< HEAD
=======

>>>>>>> track-books
        });
        
    //Sending PUT request to upload and retrieve data from api
    function putBio(url = ``, data = {}) {
                // Default options are marked with *
                  return fetch(url, {
                      method: "PUT", // *GET, POST, PUT, DELETE, etc.
                      mode: "cors", // no-cors, cors, *same-origin
                      cache: "no-cache", // *default, no-cache, reload, force-cache, only-if-cached
                      credentials: "same-origin", // include, *same-origin, omit
                      headers: {
                          "Content-Type": "application/json; charset=utf-8",
                          // "Content-Type": "application/x-www-form-urlencoded",
                      },
                      redirect: "follow", // manual, *follow, error
                      referrer: "no-referrer", // no-referrer, *client
                      //body: JSON.stringify(data), // body data type must match "Content-Type" header
                  })
                  .then(response => response.json()); // parses response to JSON
              }
})();