// 获取input输入框中的文字，调用Android中的Toast显示
function getMessage(){
    var message = document.getElementById("jsinput");
    contact.showToast(message.value);
}

// 添加一个联系人
function addPerson(persons){
    var personObjs = eval(persons);
    var table = document.getElementById("table");
    for(var i=0; i < personObjs.length; i++){
        var tr = table.insertRow(table.rows.length);
        var td1 = tr.insertCell(0);
        td1.align = "center";   // 居中
        var td2 = tr.insertCell(1);
        td2.align = "center";

        td1.innerHTML = personObjs[i].name;
        td2.innerHTML = personObjs[i].phone;
    }
}