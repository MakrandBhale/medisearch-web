var map, infoWindow;

function initMap() {
    // The location of Uluru
    var uluru = {lat: -25.344, lng: 131.036};
    // The map, centered at Uluru
    map = new google.maps.Map(
        document.getElementById('maps'), {zoom: 13, center: uluru});
    // The marker, positioned at Uluru

    infoWindow = new google.maps.InfoWindow;
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            console.log(pos);
            let marker = new google.maps.Marker({
                position: pos,

                map: map});
            infoWindow.setPosition(pos);
            infoWindow.setContent("You are here (approx. location)");
            infoWindow.open(map);
            map.setCenter(pos);
            //map.zoom(15)

        }, function() {
            handleLocationError(true, infoWindow, map.getCenter());
        });
    } else {
        alert("Browser doesn't support geolocation")
        // Browser doesn't support Geolocationghjfghjfgjh
        //handleLocationError(false, infoWindow, map.getCenter());
    }

}

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

function search(store) {
    console.log(store);
    let keyword  = document.getElementById("search-box").value;
    let client = new HttpClient();
    client.get('GetShopInfo?shopId=' + store, function(response) {
        let resJson = JSON.parse(response);
        // do something with response
        addMarker(resJson.latitude, resJson.longitude, resJson.shopname);
        //alert(resJson.latitude + "," +resJson.longitude);
    });
}

function addMarker(lat, lang, name) {

    let location = new google.maps.LatLng(lat, lang);
    let marker = new google.maps.Marker({
        position: location,
        // icon: 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAM1klEQVR4Xu1baXAUZRp+p3uOZDIk5JwcJASIBFBWQYKgXFlLXHXFUhfL0pLa8ge7gBxiDIdaRrRWZRfdBeWIB8cQE4kBQQhglShgVBR1XXFhIwECIQlJyDWZq6ePfd5OBgYWrbgbKk2xXfVVd2a6p77neZ/3+d7v6y8musoP01WOn/5PwJWkAG36Gktbjede2Ru4QwkEcxRF6Y/mkBXZI8vySUVVvtZUrTzC37plwIl1/u5guyIUoE3dJLZ1nH5c80uLKMoWJzhsRFYLaYJGsl8iucNLgVYPmpuCAYmkoNSmqPIrkY72l679Z6n0c0QYngD3va8PVdq8m01JjiFiZgKZYqPIZBGJVIU0v0KqL0Cyx0/BDh9JbR7y1zSSr7GVAoEABYNSlayq949v2PjdT5FgaAJa7l4+kXyB7eLQVIc5K4mExGgyxUQSRZhBgEZqTQtpLR5S2r0URJPaPXoLNLVRx4kzFPD5KCBJfk1V7pvU8u7OS5FgWAKa73xlOPmCX1iuz7CbBztJHJBIpmQQYLcSmUykdSDy1WeJmtykNnsoiOhzk9o7KMBKaGkHCfUk+f06CWSmibeeLf3yYhIMSYA2tcDaXG85bBmcMtA8LJXEa5JJyIwnE3LfJAikBSH/dh+px5sI6UEEAmScdQV0keB3gwRWwulG9gQSBFOtJqrZuY2lHeEkGJKA+gnPv2BxRD1luyEDkU8i8dpUEuKQ+zZIX0D0PRJprV5ST54l9ayHVMhfccMDOA0AXHLDFDkVcM0E+D0eHbMgmJdPOvvOXEMTcPamgmgkbV3UtRl2S0YiidlOEgYmkhBhIWLzw6G1+0k500Zqo5sISmA1yG4/SV6QgBGBCfDjHAQBPqSCp7EFaUMkimZZDWgpue7iphAJhlNA9cinppvt1jVRw/uTJbkvCWlxJPSLI4q0kGCG/AMyaY0dpAGkAtAE6asdEin+AEkYDSREm89+DxPARHjI09DM8QcBAlJIXDChwbXUsARUXZ+/3ZYQc5c9K5WscH0h3kEE5xej7URmkbRmgMdYr3mCMEJE3y1hKJQw/mPYYwJgepIHSgABAZxZ/qwCUqEA+IdgtXw6vm7DeMMSUDnsiabItPj4iPREssY5yAzg7PwaokeiiUw89kuobbxQgjeAWgCFkCSjIAIBqAkCPj/JPPyBHB4GfSAhCBUIGp6Hf1gibb5bTq0Fm52HoVLgeObvI3y2aJ89NYEiUuPIEh1FZkckCTaL7v6kIowYAUhSdRK0gEJKMEgyFCGzCpiEABQAEvxoQXiCD0pQA0EANZEgimTB74E45y1nXA2GI+Bg/+kpNrOlNtIZS7bkWHTWTuZIG4lWs06ASUOXgyppCoZBeIEic+6DgCAUABK4DA76EXGQwUrwe+EDIIWLJtHEChLJBlIDXiljYu3aU4Yj4Ius2dE2WWqLiHGQLQUKsEfA/a1ktsAAIV+dAEVD1KECkKACuCKBAG46eDQJKmBD9KIAAhmqourR5/w3Yf5gjYrk+9LGnXq71nAEcIe+6veo12yzRdpT4/Xom21WuL8ZBGAEYC9HNHUCMBdQIX8FaSAjHXQfQMHDKaB7AUxRkXEfuz8nOp63QlGCKKjVR5utD1Apf2ksD+AOfe6c9rloFsdExMfo+SparYS/O1OAb4ACNA0eILMPALQiI8dlCiId2AgVfBZA5RsCj6pZjz4TEBHXBykjHxp7vHC4IU2QO7U34aECdPhZC4Db0GER8heFLgKAgwBek9kP2QP4GioAaN0MoQyJfaEr8gye00jF5yKKKFtsHxRM3mVjjr+ZZ1wCYqYOUAXhmAjDMkfYyMo+AAVwBPUkUE0Y0nFmI4QHqACrIM+DaicBGo8UnPVALyKFmESeJ1iiIjo9RFKG51StPmRYArhjH8U8UIzePijAuUUYlxmNPYBB6QrgNABQhVMBZzY6Pus5jXtM/BwAR2AkYf/gNQMBzwTdvp1jjhXeGQJvSA/Q0yDuwXTM9o/Apew8hOnAdWDs55gM6QjYCzqv+OBRQtMw1kMtXDyhmiRrAqbPfD/qAP/JJkk1ydeNObzmR8MT0OkFDz4EQEWIrT6On0fK2EP1m24KncRAIeYopAzy3Ny3s4I0220kIPe9h2tIbvX+YfSPqwrDwRtWAaFOfhL/cD7U/DJXgDyas/zDQo7yFirADVzj8IjBVaPYx455QyQUgOiDFG9lLcktnhdHH3l98cXgDU8Ad3CP8+FHLZppFTRuVWF8/xHBUHpgrmDpA+BJfckC+RN8wX+iQdGk4LycIytfuxT4K4IA7uTOUTOyA37/XmeHgLUxXhBl00NaQAFc35sAXkC5jJkeiZg3YMkcK8X+CtFu/WPOP5afc/wrUgHc6ZUzFsa2D4hrUH6oNcd8U0/DJAc5VBDBBRLrnw9eJJWVM3gvsBOfvzWmcuWnPxX1K8IEwzv56tK/TE/JSF9TUfEZ1dXXUXJaP8+EQ/Kw+EYpuY9Xi+BlEix6nhx1uLCuO6CvOAKKior2OxzR48p3llNkpINioqI2PvdCwSO/FOwVmQLFxcWp6ekZNSeqq02sgEFZWdTu9ty55NmnLrnO/0tJMdSCyKU671rvmn/9yBHLdu3aRfX1DZQ5YEBzc9OZxIKCgrAx8ZfCPn+/4QkoKyv72ulMHrl5y/sUn5BINot1dV7evBn/PeQLnzQ0AZtcrqzM7CE/VlUdo/0VFZSdPYSXuMYvXJjXLYfvDkmGJsC1fv0zo0bftGTbtg+osamJMvpn1s55bEZad4B19x5DE7Bt67YjiUlJ2ZtK36MkJ9dAlmX5efPOzeW7C/Ln7jMsAZs2bbohe3D2t0cqK2nvvv00ZMhQ8ri9IxcteuLbngAe+g3DEuByuZaOGzf+yeKSd6mlpZXS09OPzpk985qeBG/oucCODz446UxNTXe5iiglNQ2rQaYl+fnzn70qCEDxc/OIESMqvj/0A33yyV7KhvzbvO7Bz+TnX7CY0RNkGDIFQMDK3NzcGW+8+TZ14LVWalra3+fOnjmiJwBf/BuGIwAVnnDz2LH1Sc7kxLfXrqN+6ek8681f8OT8P18VBJSWlk7OyRm9+6uDB3X5s/u3tfr6Pf30/NNXBQGuDRvW//buKdOWr1hBfrzqcianVMybM3Pc5QBvuFEAY7/VmZTUFBef0KfwjTe58sOavzJzYf4Tq64KAkpLSu6bkHtr2d59e2kfih+4v+pua05avHgxtoNdnsNQJvhuScl7U+655/4/vfgy1jRVSkpy7po3Z9Ydlwd6568ahoCPP/7YEWGzNUX1ibYVFr6Bef9Aft83bcGCPNdVQQDG/kcmT568oXznLjpw4CvIf0jgbKOSUFAw64J9fT1NhmEUUFZa9uHE3Em3vfLqX/Wlbhhh2fy5j/2upwEbshDavXt3UmxsbB3e8gpF7xTTwEGD+H3//Qvy8jZf1OFQwPgcHryfug5/PPR+jc/nmiEUUFJSMjs399fLt2Lh4/vvD2HlJ9vd1FgP+RfwVnd9fwca75Lkcwh86HN940gY0ov/Dn3V+d4c74zQ+Jpb0BAEbC4r+2zkjaPGrl5TSFabjfrG9HXNf3zOtK4OY4sohRoDDZEQDrQ7CmAiQpFnEphcf68TsGPHjv4J8QknWtvbaevWbTRoUBZJgcDtixblf9gVdWwQJuyRJ2wTx+uPC1XQ3ZQOlz+Dxx4TwvYx8vU6AYWrVy+49bbbXtry/lY6erQK6/7XNHncrc6uZW/uHwMPERARRkJ4Ll8KRzjokOT5zOCDHH1DpMC6t9Z+lz1s6K9cG4vIbo8ii8W66sU/LZnZFVrOe5Y//kdGJ4Ebq4CPEBg+h20gOCcK/iz0HZ858twYPJPATelVBaxYsWLIwAEDD59paKSP9uyhzMyBdKyqanxxseuzLqAMnhXAkQ+lAPeZO885zGC4hRPA34eAh1QSroDQtf5MrxLw/HPPLZkwcdIz28vLqa62jtL69asp2rhhcE1NDUeZAXPkmYTOffLnI6gbWCiK52J+IREh8Px1+HXY7b1MwNKXXj6KBY9B23eUU5TDQbWna17bsWP7C10RZ+Cde2DOR5yNi4EzAaHh7FLyvwDkz/3RawqYO3duznVDh31ZW3+GDn7zNWVk9KdtW7fcc+rUqcou4Po4jaa7ddc5JPkeeS/YaykwdepU3vy+7PbJt8/dh1dezc0tFNO37wnXhnVTuiIeijRHm8FzxDnvewx4SBW9oQBTVlaWdcpdd5/AW5/k/RWfksPRh6qrq18/cODz5V0Sx39CnZP6ZQHeqwT8ZvLk2TeOyvlbA9z/aNVRSk5Owfrfnml1dXXlXVK/bBG/2A96QwE0a9aswcj1OyorK9ODwaASGRn5r7a2tl34rLFL6v+TsXXbAXHjvwFVceedmBkaRgAAAABJRU5ErkJggg==',
        map: map
    });
    map.setZoom(15);
    map.panTo(marker.position);
    let contentString = '<h6>'+name+'</h6>';

    let window = new google.maps.InfoWindow({
        content: contentString
    });

    marker.addListener('click', function() {
        window.open(map, marker);
    });
}
