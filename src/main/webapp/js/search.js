let HttpClient = function() {
    this.get = function(aUrl, aCallback) {
        let anHttpRequest = new XMLHttpRequest();
        anHttpRequest.onreadystatechange = function() {
            if (anHttpRequest.readyState === 4 && anHttpRequest.status === 200)
                aCallback(anHttpRequest.responseText);
        };

        anHttpRequest.open( "GET", aUrl, true );
        anHttpRequest.send( null );
    }
};
function search() {
    console.log("called");
    let keyword  = document.getElementById("search-box").value;
    let client = new HttpClient();
    client.get('search?keyword=' + keyword, function(response) {
        // do something with response
        alert(response);
    });
}
