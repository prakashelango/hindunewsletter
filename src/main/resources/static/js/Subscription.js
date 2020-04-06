let serviceUrl= "http://localhost:8080";

function subscribe() {
    if (ValidateEmail(getSubscriptionPayloadData().emailid)){
        $.ajax({
            url: serviceUrl+"/subscribe",
            type: "POST",
            data: getSubscriptionPayloadData,
            dataType: "application/json",
            success: function(result){
                alert("Subscribed");
            }});
    }

}

function getSubscriptionPayloadData() {
    var formData = {userid:"",emailid:$("#Email").val()};
    return formData;
}

function ValidateEmail(mail) {
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail))
    {
        return (true)
    }
    alert("You have entered an invalid email address!")
    return (false)
}