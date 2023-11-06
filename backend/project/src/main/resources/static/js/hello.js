console.log('Hello World Script Loaded');

(function(){
    function reqListener() {
        console.log('Our API responde:' + this.responseText);
    }
    var oReq = new XMLHttpRequest();
    oReq.addEventListener('load', reqListener);
    oReq.open('GET', 'api/hello?name=Daniel Bastos')
    oReq.send()
})();