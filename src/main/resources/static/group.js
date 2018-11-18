// Example POST method implementation:


(function(){
	var submitButton = document.getElementById("button");
	var bookTitle = document.getElementById("booktitle").value;
	var pageCount = document.getElementById("pageCount");
	var dataHolder;
	submitButton.addEventListener('click', function(){
		//event.preventDefault();
		onClickEvent();
	})
	console.log(bookTitle)
	
	function onClickEvent(){
//		getData(`https://www.googleapis.com/books/v1/volumes?q=${bookTitle}&maxResults=1`, {answer: 42})
//			.then(data => console.log(JSON.stringify(data))) // JSON-string from `response.json()` call
//			.catch(error => console.log(error));
		//dataHolder = data;
	
		var bookTitle = document.getElementById("booktitle").value;
		var bookForm = document.getElementById("bookForm");
		
		fetch(`https://www.googleapis.com/books/v1/volumes?q=${bookTitle}&maxResults=1`)
		  .then(function(response) {
		    return response.json();
		    var result = response.json();
		  })
		  .then(function(myJson) {
		    //console.log(JSON.stringify(myJson));
		    var desc = myJson.items;
			console.log(desc)
			pageCount.value = desc[0].volumeInfo.pageCount;
			console.log(pageCount)
			bookForm.submit();
		  });
	}
	
//	function sentPostRequest(title){
//		postData(`https://www.googleapis.com/books/v1/volumes?q=${bookTitle}`, {answer: 42})
//			.then(data => console.log(JSON.stringify(data))) // JSON-string from `response.json()` call
//			.catch(error => console.log(error));
//		var pageCount = data.volumeInfo.pageCount;
//		alert(pageCount);
//	}

	function getData(url = ``, data = {}) {
	  // Default options are marked with *
	    return fetch(url, {
	        method: "GET", // *GET, POST, PUT, DELETE, etc.
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
	
	function postData(url = ``, data = {}) {
		  // Default options are marked with *
		    return fetch(url, {
		        method: "POST", // *GET, POST, PUT, DELETE, etc.
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
	
	
	
})()
