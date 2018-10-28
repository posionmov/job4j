var marks = [];
var bodies = [];
var trans = [];
var colors = [];
var engines = [];
var drives = [];
var ads = [];
var curUser = {};
var isLoged;
var all = false;

// Метод, старабывающий при загрузке страницы
// Наполняет HTML дефолтными данными
$.ajax({
    url: "./list",
    method: "get",
    success: function (data) {
        var parsedData = JSON.parse(data);
        marks = parsedData["marks"];
        bodies = parsedData["bodies"];
        trans = parsedData["transmissions"];
        colors = parsedData["colors"];
        engines = parsedData["engines"];
        drives = parsedData["drives"];
        ads = parsedData["ads"];
        curUser = parsedData["curUser"];
        isLoged = parsedData["login"];
        if (!isLoged) {
            document.getElementById("createAdHeader").style.visibility = "hidden";
            document.getElementById("headLogOut").style.visibility = "hidden";
            document.getElementById("headLogin").style.visibility = "visible";
        } else {
            document.getElementById("headLogOut").style.visibility = "visible";
            document.getElementById("createAdHeader").style.visibility = "visible";
            document.getElementById("headLogin").style.visibility = "hidden";
        }
        fillMarks();
        fillBodies();
        fillTransmissions();
        fillColor();
        fillEngines();
        fillDrives();
        fillRudders();
        fillAds(false);
    }
});

function showToday() {
    fillAds(false, true)
}

// Метод наполнения модала данными из обьявления
function fillModal(adId) {
    document.getElementById("modalMark").innerText = ads[adId]["car"]["mark"]["descr"];
    document.getElementById("modalModel").innerText = ads[adId]["car"]["carModel"]["descr"];
    document.getElementById("modalBody").innerText = ads[adId]["car"]["bodyType"]["descr"];
    document.getElementById("modalYear").innerText = ads[adId]["car"]["yearOfManufactured"];
    document.getElementById("modalMills").innerText = ads[adId]["car"]["mileage"];
    document.getElementById("modalTrans").innerText = ads[adId]["car"]["transmission"]["descr"];
    document.getElementById("modalEngine").innerText = ads[adId]["car"]["engine"]["descr"];
    document.getElementById("modalEngineCapacity").innerText = ads[adId]["car"]["engineCapacity"];
    document.getElementById("modalPower").innerText = ads[adId]["car"]["power"];
    document.getElementById("modalDrive").innerText = ads[adId]["car"]["drive"]["descr"];
    var rudder = (ads[adId]["car"]["leftRudder"] == true) ? "Леворульная" : "Праворульная";
    document.getElementById("modalRudder").innerText = rudder;
    document.getElementById("modalColor").innerText = ads[adId]["car"]["carColor"]["descr"];
    document.getElementById("modalPrice").innerText = ads[adId]["car"]["price"];
    document.getElementById("modalSeller").innerText = ads[adId]["user"]["name"];
    document.getElementById("modalConnection").innerText = ads[adId]["user"]["email"];
}

// Метод наполнения таблицы обьявлений
function fillAds(showAll, showToday) {
    var curDate = new Date();
    var tableAds = document.getElementById("tableBody");
    tableAds.innerHTML = "";
    for (var i = 0; i < ads.length; i++) {
        if (showAll || (showToday && (curDate.getFullYear() === new Date(ads[i]["createDate"]).getFullYear())
                    && (curDate.getMonth() === new Date(ads[i]["createDate"]).getMonth())
                    && (curDate.getUTCDate() === new Date(ads[i]["createDate"]).getUTCDate())) || !ads[i]["close"]) {
            tableAds.innerHTML += "<tr><td>" + ads[i]["id"] + "</td>\n"
                + "<td><img src=\"." + ads[i]["pathToImage"] + "\" width='220' height='117' class='adPicture'></td>"
                + "<td> "
                + "<div><label style='margin-right: 10px'>Марка: </label>" + ads[i]["car"]["mark"]["descr"] + "</div>"
                + "<div><label style='margin-right: 10px'>Цена: </label>" + ads[i]["car"]["price"] + "</div>"
                + "<div><label style='margin-right: 10px'>Описание: </label>" + ads[i]["description"] + "</div>"
                + "<div><label style='margin-right: 10px'>Цвет автомобиля: </label>" + ads[i]["car"]["carColor"]["descr"] + "</div>"
                + "</td>\n"
                + "<td>\n"
                +"<div class=\"btn-group-vertical\">\n"
                + fillButtons(i)
                +"</div>\n"
                + "</td></tr>";
        }
    }
}

// Метод создания кнопок
function fillButtons(i) {
    var result = "<button type=\"button\" class=\"btn btn-default\" onclick='fillModal(" + i + ")' data-toggle=\"modal\" data-target=\"#adModal\">Обьявление</button>";
    if (curUser["id"] == ads[i]["user"]["id"] || (isLoged && curUser["role"]["id"] == 1)) {
        result += "<button type=\"button\" class=\"btn btn-default\" onclick='closeAd(" + ads[i]["id"] + ")'>Удалить обьявление</button>";
    }
    return result;
}

// Метод наполнения поля выбора типа руля (левый или правый)
function fillRudders() {
    var rudderSelect = document.getElementById("rudderSelect");
    var modalRudderSelect = document.getElementById("modalRudderSelect");
    rudderSelect.options[1] = new Option("Левый", "true");
    modalRudderSelect.options[1] = new Option("Левый", "true");
    rudderSelect.options[2] = new Option("Правый", "false");
    modalRudderSelect.options[2] = new Option("Правый", "false");
}

// Метод наполнения поля выбора типа привода
function fillDrives() {
    var driveSelect = document.getElementById("driveSelect");
    var modalDriveSelect = document.getElementById("modalDriveSelect");
    for (var i = 0; i < bodies.length; i++) {
        driveSelect.options[i + 1] = new Option(drives[i]["descr"], drives[i]["id"]);
        modalDriveSelect.options[i + 1] = new Option(drives[i]["descr"], drives[i]["id"]);
    }
}

// Метод наполнения поля выбора типа двигателя (бензин, дизель и тп)
function fillEngines() {
    var engineSelect = document.getElementById("engineSelect");
    var modalEngineSelect = document.getElementById("modalEngineSelect");
    for (var i = 0; i < bodies.length; i++) {
        engineSelect.options[i + 1] = new Option(engines[i]["descr"], engines[i]["id"]);
        modalEngineSelect.options[i + 1] = new Option(engines[i]["descr"], engines[i]["id"]);
    }
}

// Метод наполнения поля выбора цветв
function fillColor() {
    var colorSelect = document.getElementById("colorSelect");
    var modalColorSelect = document.getElementById("modalColorSelect");
    for (var i = 0; i < bodies.length; i++) {
        colorSelect.options[i + 1] = new Option(colors[i]["descr"], colors[i]["id"]);
        modalColorSelect.options[i + 1] = new Option(colors[i]["descr"], colors[i]["id"]);
    }
}

// Метод наполнения поля выбора коробки передач
function fillTransmissions() {
    var transSelect = document.getElementById("transSelect");
    var modalTransSelect = document.getElementById("modalTransSelect");
    for (var i = 0; i < bodies.length; i++) {
        transSelect.options[i + 1] = new Option(trans[i]["descr"], trans[i]["id"]);
        modalTransSelect.options[i + 1] = new Option(trans[i]["descr"], trans[i]["id"]);
    }
}

// Метод наполнения поля выбора кузовов
function fillBodies() {
    var bodySelect = document.getElementById("bodySelect");
    var modalBodySelect = document.getElementById("modalBodySelect");
    for (var i = 0; i < bodies.length; i++) {
        bodySelect.options[i + 1] = new Option(bodies[i]["descr"], bodies[i]["id"]);
        modalBodySelect.options[i + 1] = new Option(bodies[i]["descr"], bodies[i]["id"]);
    }
}

// Метод наполнения поля марок автомобилей
function fillMarks() {
    var markField = document.getElementById("marcSelect");
    var modalMarcSelect = document.getElementById("modalMarcSelect");
    for (var i = 0; i < marks.length; i++) {
        markField.options[i + 1] = new Option(marks[i]["descr"], marks[i]["id"]);
        modalMarcSelect.options[i + 1] = new Option(marks[i]["descr"], marks[i]["id"]);
    }
}

// Метод наполнения поля моделей при изменении поля марки
function onChangeMark() {
    var markValue = document.getElementById("marcSelect").value;
    var modalMarcSelect = document.getElementById("modalMarcSelect").value;
    var modelField = document.getElementById("modelSelect");
    var modalModelSelect = document.getElementById("modalModelSelect");
    for (var i = 0; i < marks.length; i++) {
        if (marks[i]["id"] == markValue || marks[i]["id"] == modalMarcSelect) {
            for (var j = 0; j < marks[i]["models"].length; j++) {
                modelField.options[j + 1] = new Option(marks[i]["models"][j]["descr"], marks[i]["models"][j]["id"]);
                modalModelSelect.options[j + 1] = new Option(marks[i]["models"][j]["descr"], marks[i]["models"][j]["id"]);
            }
        }
    }
}

// Метод вывода года при вводе
function onInputYear() {
    if (document.getElementById("yearInput").value == 2019) {
        document.getElementById("yearShow").innerText = "Все";
    } else {
        document.getElementById("yearShow").innerText = document.getElementById("yearInput").value;
    }
}

// Метод вывода пробега при вводе
function onInputMills() {
    if (document.getElementById("millsInput").value == 900001) {
        document.getElementById("millsShow").innerText = "Все";
    } else {
        document.getElementById("millsShow").innerText = document.getElementById("millsInput").value;
    }
}

// Метод вывода обьема двигателя при вводе
function onInputCapacity() {
    if (document.getElementById("capacityInput").value == 7) {
        document.getElementById("capacityShow").innerText = "Все";
    } else {
        document.getElementById("capacityShow").innerText = document.getElementById("capacityInput").value;
    }
}

// Метод вывода мощности двигателя при вводе
function onInputPower() {
    if (document.getElementById("powerInput").value == 500) {
        document.getElementById("powerShow").innerText = "Все";
    } else {
        document.getElementById("powerShow").innerText = document.getElementById("powerInput").value;
    }
}

// Метод авторизации
function auth() {
    var login = document.getElementById("login").value;
    var password = document.getElementById("password").value;
    $.ajax({
        url: "./auth",
        method: "post",
        data: {
            login : login,
            password : password
        },
        success: function (data) {
            if (JSON.parse(data)["auth"] === true) {
                document.location.reload();
            } else {
                document.getElementById("incorrect").innerText = "Не верный логин или пароль";
            }
        }
    });
}

// Метод создания небольшого окна регистрации
function goToRegister() {
    window.open("./register.html", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,top=500,left=500,width=422,height=420");
}

// Метод создания обьявления
function createAd() {
    $.ajax({
        url: "./add",
        method: "post",
        data: {
            description: document.getElementById("modalAddDescr").value,
            year: document.getElementById("addModalYear").value,
            mills: document.getElementById("addModalMills").value,
            capacity: document.getElementById("addModalCapacity").value,
            power: document.getElementById("addModalPower").value,
            left: document.getElementById("modalRudderSelect").value,
            price: document.getElementById("modalAddPrice").value,
            body: document.getElementById("modalBodySelect").value,
            mark: document.getElementById("modalMarcSelect").value,
            trans: document.getElementById("modalTransSelect").value,
            engine: document.getElementById("modalEngineSelect").value,
            drive: document.getElementById("modalDriveSelect").value,
            color: document.getElementById("modalColorSelect").value,
            model: document.getElementById("modalModelSelect").value
        },
        success: function (data) {
            if (JSON.parse(data)["newAd"] == true) {
                addPicture();
                window.location.reload();
            }
        }
    });
}

// Метод добавления картинки к обьявлению
function addPicture() {
    var file_data = $("#avatar").prop("files")[0]; // Getting the properties of file from file field
    var form_data = new FormData(); // Creating object of FormData class
    form_data.append("file", file_data); // Appending parameter named file with properties of file_field to form_data
    $.ajax({
        url: "./saveFile", // Upload Script
        dataType: 'script',
        cache: false,
        contentType: false,
        processData: false,
        data: form_data, // Setting the data attribute of ajax with file_data
        type: 'post',
        async: false,
        success: function(data) {
            // Do something after Ajax completes
        }
    });
}

// Метод выхода
// Уничтожает данные в сессии и перезагружает страницу
function signOut() {
    $.ajax({
        url: "./auth",
        method: "get",
        data: {
            signOut : true,
        },
        success: function (data) {
            window.location.reload();
        }
    });
}

// Метод поиска по обьявлениям
function findAds() {
    var from = document.getElementById("priceFrom").value == "" ? 0 : document.getElementById("priceFrom").value;
    var to = document.getElementById("priceTo").value == "" ? 0 : document.getElementById("priceTo").value;
    var year = document.getElementById("yearInput").value == 2019 ? 0 : document.getElementById("yearInput").value;
    var mills = document.getElementById("millsInput").value == 900001 ? 0 : document.getElementById("millsInput").value;
    var capacity = document.getElementById("capacityInput").value == 7 ? 0 : document.getElementById("capacityInput").value;
    var power = document.getElementById("powerInput").value == 500 ? 0 : document.getElementById("powerInput").value;
    $.ajax({
        url: "./search",
        method: "get",
        data: {
            mark : document.getElementById("marcSelect").value,
            model : document.getElementById("modelSelect").value,
            body : document.getElementById("bodySelect").value,
            year : year,
            mileage : mills,
            trans : document.getElementById("transSelect").value,
            engine : document.getElementById("engineSelect").value,
            capacity : capacity,
            power : power,
            drive : document.getElementById("driveSelect").value,
            left : document.getElementById("rudderSelect").value,
            color : document.getElementById("colorSelect").value,
            priceFrom : from,
            priceTo : to
        },
        success: function (data) {
            ads = JSON.parse(data)["ads"];
            fillAds()
        }
    });
}

// метод для закрытия обьявления
function closeAd(id) {
    $.ajax({
        url: "./list",
        method: "post",
        data: {
            id : id
        },
        success: function (data) {
            var data = JSON.parse(data);
            if (data["close"] == true) {
                window.location.reload();
            }
        }
    });
}

function showAll() {
    if (!all) {
        fillAds(true);
        document.getElementById("btnShow").innerText = "Показать только открытые";
    } else {
        fillAds(false);
        document.getElementById("btnShow").innerText = "Показать все";
    }
    all = !all;
}

function register() {
    $.ajax({
        url: "./reg",
        method: "post",
        data: {
            name : document.getElementById("name").value,
            login : document.getElementById("login").value,
            password : document.getElementById("password").value,
            email : document.getElementById("email").value
        },
        success: function (data) {
            if (JSON.parse(data)["add"] == "success") {
                window.close();
            } else {
                alert("Не верный логин или пароль");
            }
        }
    });
}