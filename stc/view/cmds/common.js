function validatetype(type, value, obj = Object(), checkLength = false) {
  if (isNaN(value) && isPrimitiveType(type)) {
    obj["message"].push(`Exists NaN in type ${type}`);
    return false;
  }
  switch (type) {
    case "byte":
      var min = -(Math.pow(2, 8) / 2);
      var max = (Math.pow(2, 8) / 2) - 1;
      break;
    case "short":
      var min = -(Math.pow(2, 16) / 2);
      var max = (Math.pow(2, 16) / 2) - 1;
      break;
    case "int":
      var min = -(Math.pow(2, 32) / 2);
      var max = (Math.pow(2, 32) / 2) - 1;
      break;
    case "long":
      var min = -(Math.pow(2, 64) / 2);
      var max = (Math.pow(2, 64) / 2) - 1;
      break;
    case "double":
      var min = -Number.MAX_VALUE;
      var max = Number.MAX_VALUE;
      break;
    case "string8":
      len = value.length;
      return validatetype("byte", len, obj, true);
    case "string16":
      len = value.length;
      return validatetype("short", len, obj, true);
    case "string32":
      len = value.length;
      return validatetype("int", len, obj, true);
    case "bytes8":
      len = value.length;
      for (let index = 0; index < value.length; index++) {
        const element = value[index];
        if (!validatetype("byte", element, obj)) {
          return false;
        }
      }
      return validatetype("byte", len, obj, true);
    case "bytes16":
      len = value.length;
      for (let index = 0; index < value.length; index++) {
        const element = value[index];
        if (!validatetype("byte", element, obj)) {
          return false;
        }
      }
      return validatetype("short", len, obj, true);
    case "bytes32":
      len = value.length;
      for (let index = 0; index < value.length; index++) {
        const element = value[index];
        if (!validatetype("byte", element, obj)) {
          //console.log(element);
          return false;
        }
      }
      return validatetype("int", len, obj, true);
    case "shorts8":
      len = value.length;
      for (let index = 0; index < value.length; index++) {
        const element = value[index];
        if (!validatetype("short", element, obj)) {
          return false;
        }
      }
      return validatetype("byte", len, obj, true);
    case "shorts16":
      len = value.length;
      for (let index = 0; index < value.length; index++) {
        const element = value[index];
        if (!validatetype("short", element, obj)) {
          return false;
        }
      }
      return validatetype("short", len, obj, true);
    case "shorts32":
      len = value.length;
      for (let index = 0; index < value.length; index++) {
        const element = value[index];
        if (!validatetype("short", element, obj)) {
          return false;
        }
      }
      return validatetype("int", len, obj, true);
    case "ints8":
      len = value.length;
      for (let index = 0; index < value.length; index++) {
        const element = value[index];
        if (!validatetype("int", element, obj)) {
          return false;
        }
      }
      return validatetype("byte", len, obj, true);
    case "ints16":
      len = value.length;
      for (let index = 0; index < value.length; index++) {
        const element = value[index];
        if (!validatetype("int", element, obj)) {
          return false;
        }
      }
      return validatetype("short", len, obj, true);
    case "ints32":
      len = value.length;
      for (let index = 0; index < value.length; index++) {
        const element = value[index];
        if (!validatetype("int", element, obj)) {
          return false;
        }
      }
      return validatetype("int", len, obj, true);
    case "longs8":
      len = value.length;
      for (let index = 0; index < value.length; index++) {
        const element = value[index];
        if (!validatetype("long", element, obj)) {
          return false;
        }
      }
      return validatetype("byte", len, obj, true);
    case "longs16":
      len = value.length;
      for (let index = 0; index < value.length; index++) {
        const element = value[index];
        if (!validatetype("long", element, obj)) {
          return false;
        }
      }
      return validatetype("short", len, obj, true);
    case "longs32":
      len = value.length;
      for (let index = 0; index < value.length; index++) {
        const element = value[index];
        if (!validatetype("long", element, obj)) {
          return false;
        }
      }
      return validatetype("int", len, obj, true);
    case "string8s8":
      len = value.length;
      for (let index = 0; index < value.length; index++) {
        const element = value[index];
        if (!validatetype("string8", element, obj)) {
          return false;
        }
      }
      return validatetype("byte", len, obj, true);
    case "string8s16":
      len = value.length;
      for (let index = 0; index < value.length; index++) {
        const element = value[index];
        if (!validatetype("string8", element, obj)) {
          return false;
        }
      }
      return validatetype("short", len, obj, true);
    case "string8s32":
      len = value.length;
      for (let index = 0; index < value.length; index++) {
        const element = value[index];
        if (!validatetype("string8", element, obj)) {
          return false;
        }
      }
      return validatetype("int", len, obj, true);
    case "string16s8":
      len = value.length;
      for (let index = 0; index < value.length; index++) {
        const element = value[index];
        if (!validatetype("string16", element, obj)) {
          return false;
        }
      }
      return validatetype("byte", len, obj, true);
    case "string16s16":
      len = value.length;
      for (let index = 0; index < value.length; index++) {
        const element = value[index];
        if (!validatetype("string16", element, obj)) {
          return false;
        }
      }
      return validatetype("short", len, obj, true);
    case "string16s32":
      len = value.length;
      for (let index = 0; index < value.length; index++) {
        const element = value[index];
        if (!validatetype("string16", element, obj)) {
          return false;
        }
      }
      return validatetype("int", len, obj, true);
    case "string32s8":
      len = value.length;
      for (let index = 0; index < value.length; index++) {
        const element = value[index];
        if (!validatetype("string32", element, obj)) {
          return false;
        }
      }
      return validatetype("byte", len, obj, true);
    case "string32s16":
      len = value.length;
      for (let index = 0; index < value.length; index++) {
        const element = value[index];
        if (!validatetype("string32", element, obj)) {
          return false;
        }
      }
      return validatetype("short", len, obj, true);
    case "string32s32":
      len = value.length;
      for (let index = 0; index < value.length; index++) {
        const element = value[index];
        if (!validatetype("string32", element, obj)) {
          return false;
        }
      }
      return validatetype("int", len, obj, true);
  }
  if (type.includes("tuple8")) {
    tuple_types = type.split("|")[1].split("-");
    let allTrue = true;
    value.forEach(es => {
      if (!allTrue) return;
      if (es.length != tuple_types.length) {
        obj['message'].push(`(${tuple_types}) != (${es})`);
        allTrue = false;
        return;
      }
      es.forEach((e, i) => {
        if (!validatetype(tuple_types[i], e, obj)) allTrue = false;
      });
    });
    len = value.length;
    return validatetype("byte", len, obj, true) && allTrue;
  }
  else if (type.includes("tuple16")) {
    tuple_types = type.split("|")[1].split("-");
    let allTrue = true;
    value.forEach(es => {
      if (!allTrue) return;
      if (es.length != tuple_types.length) {
        obj['message'].push(`(${tuple_types}) != (${es})`);
        allTrue = false;
        return;
      }
      es.forEach((e, i) => {
        if (!validatetype(tuple_types[i], e, obj)) allTrue = false;
      });
    });
    len = value.length;
    return validatetype("short", len, obj, true) && allTrue;
  }
  else if (type.includes("tuple32")) {
    tuple_types = type.split("|")[1].split("-");
    let allTrue = true;
    value.forEach(es => {
      if (!allTrue) return;
      if (es.length != tuple_types.length) {
        obj['message'].push(`(${tuple_types}) != (${es})`);
        allTrue = false;
        return;
      }
      es.forEach((e, i) => {
        if (!validatetype(tuple_types[i], e, obj)) allTrue = false;
      });
    });
    len = value.length;
    return validatetype("int", len, obj, true) && allTrue;
  }
  if (min <= value && value <= max) {
    return true;
  }
  else {
    if (checkLength) {
      obj['message'].push(`list is over the limit`);
    }
    else {
      obj['message'].push(`Bound Error! ${type}:${value}`);
    }
    return false;
  }
}
function isPrimitiveType(type) {
  switch (type) {
    case "byte":
    case "short":
    case "int":
    case "long":
    case "double":
      return true;
  }
  return false;
}
function convertdata(type, value, isTuple = false) {
  if (isTuple) {
    switch (type) {
      case "byte":
      case "short":
      case "int":
      case "long":
      case "double":
        return value.map(x => Number(x));
    }
  }
  else {
    switch (type) {
      case "byte":
      case "short":
      case "int":
      case "long":
      case "double":
        value = Number(value)
        break;
      case "bytes8":
      case "bytes16":
      case "bytes32":
      case "shorts8":
      case "shorts16":
      case "shorts32":
      case "ints8":
      case "ints16":
      case "ints32":
      case "longs8":
      case "longs16":
      case "longs32":
        return value.map(x => Number(x));
    }
  }
  return value;

}
function validate(typedom, doms) {
  doms.each((i, dom) => {
    $(dom).off();
    $(dom).on("keypress", function (event) {
      let type = $(typedom).children("option:selected").val() == undefined ? $(typedom).text() : $(typedom).children("option:selected").val();

      if (type == "byte" || type == "int" || type == "short" || type == "long" || type == "bytes16" || type == "bytes32" || type == "shorts16" || type == "shorts32" || type == "ints16" || type == "ints32" || type == "longs16" || type == "longs32") {
        $(this).val($(this).val().replace(/[^\d]/, ""));
        if ((event.which < 48 || event.which > 57)) {
          event.preventDefault();
        }
      }
      else if (type == "double") {
        $(this).val($(this).val().replace(/[^0-9\.]/g, ''));
        if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
          event.preventDefault();
        }
      }
    });
  });
}
function validate1(type, doms) {
  doms.each((i, dom) => {
    $(dom).off();
    $(dom).on("keypress", function (event) {
      if (type == "byte" || type == "int" || type == "short" || type == "long" || type == "bytes16" || type == "bytes32" || type == "shorts16" || type == "shorts32" || type == "ints16" || type == "ints32" || type == "longs16" || type == "longs32") {
        $(this).val($(this).val().replace(/[^\d]/, ""));
        if ((event.which < 48 || event.which > 57)) {
          event.preventDefault();
        }
      }
      else if (type == "double") {
        $(this).val($(this).val().replace(/[^0-9\.]/g, ''));
        if ((event.which != 46 || $(this).val().indexOf('.') != -1) && (event.which < 48 || event.which > 57)) {
          event.preventDefault();
        }
      }
    });
  });
}
function isListType(type) {
  switch (type) {
    case "bytes8":
    case "bytes16":
    case "bytes32":
    case "shorts8":
    case "shorts16":
    case "shorts32":
    case "ints8":
    case "ints16":
    case "ints32":
    case "longs8":
    case "longs16":
    case "longs32":
    case "string8s8":
    case "string16s8":
    case "string32s8":
    case "string8s16":
    case "string16s16":
    case "string32s16":
    case "string8s32":
    case "string16s32":
    case "string32s32":
      return true;
    default:
      return false;
  }
}
function isTupleType(type) {
  if (type.includes('tuple')) return true;
  else return false;
}

function paramBehavious(paramEle, isForceShow = false) {
  let value = paramEle.find('.value');
  let type = paramEle.find('#type');
  let addbtn = paramEle.find('#add');
  let rmbtn = paramEle.find('#minus');
  let add_tuplebtn = paramEle.find('#add-value-tuple');
  let rm_tuplebtn = paramEle.find('#minus-value-tuple');
  if (isForceShow) {
    addbtn.show();
    rmbtn.show()
    add_tuplebtn.show();
    rm_tuplebtn.show()
  }
  else {
    addbtn.hide();
    rmbtn.hide();
    add_tuplebtn.hide();
    rm_tuplebtn.hide();
  }
  // validate(type, value);
}

function makeHeaderFeature(paramEle) {
  paramEle.find(".card-header-right .close-card").on('click', function () {
    var $this = $(this);
    $this.parents('.card-sub').animate({
      'opacity': '0',
      '-webkit-transform': 'scale3d(.3, .3, .3)',
      'transform': 'scale3d(.3, .3, .3)'
    });

    setTimeout(function () {
      $this.parents('.card-sub').remove();
    }, 800);
  });

  paramEle.find(".card-header-right .minimize-card").on('click', function () {
    var $this = $(this);
    var port = $($this.parents('.card-sub'));
    var card = $(port).children('.card-block').slideToggle();
    $(this).toggleClass("icon-minus").fadeIn('slow');
    $(this).toggleClass("icon-plus").fadeIn('slow');
  });
  paramEle.find(".card-header-right .full-card").on('click', function () {
    var $this = $(this);
    var port = $($this.parents('.card-sub'));
    port.toggleClass("full-card");
    $(this).toggleClass("icon-maximize");
    $(this).toggleClass("icon-minimize");
  });
}
function typeChangeEvent(paramEle, isUpdate = false, response = null) {
  let value = paramEle.find('.value');
  let type = paramEle.find('#type');
  let addbtn = paramEle.find('#add');
  let rmbtn = paramEle.find('#minus');
  let addzone = paramEle.find('.value-zone');
  let add_tuplebtn = paramEle.find('#add-value-tuple');
  let rm_tuplebtn = paramEle.find('#minus-value-tuple');
  let tuple_values_zone = paramEle.find('#tuple-values');
  console.log(type.val());
  addbtn.on("click", () => {
    ele_dom = $(`
      <div class="row">
        <div class="col-12 ">
          <div class="form-group">
            <label for="type">Element Type</label>
            <select name="type" id="subtype" class="form-control">
              ${getTypesForTuple()}
            </select>
          </div>
        </div>
      </div>
      `);
    ele_dom.find('input').tagsinput({
      allowDuplicates: true
    });
    addzone.append(ele_dom);
    paramBehavious(paramEle, isTupleType(type.val()));
  });
  add_tuplebtn.on("click", () => {
    ele = $(`
    <div class="form-group">
      <label for="value">A tuple value</label>
      <input type="text" name="value" id="value" class="form-control value">
    </div>
    `);
    ele.find('input[name="value"]').tagsinput({
      allowDuplicates: true
    });
    tuple_values_zone.append(ele);
  });
  rm_tuplebtn.on("click", () => {
    if (tuple_values_zone.find(".form-group").length <= 1) return;
    tuple_values_zone.find(".form-group:last-child").remove();
  });
  rmbtn.on("click", () => {
    if (addzone.find(".row").length <= 1) return;
    addzone.find(".row:last-child").remove();
  });
  type.change(() => {
    paramBehavious(paramEle, isTupleType(type.val()));
    addzone.empty();
    tuple_values_zone.empty();
    if (isTupleType(type.val())) {
      paramEle.find('.value-zone').empty();
      ele_dom = $(`
      <div class="row">
        <div class="col-12 ">
          <div class="form-group">
            <label for="type">Element Type</label>
            <select name="type" id="subtype" class="form-control">
              ${getTypesForTuple()}
            </select>
          </div>
        </div>
      </div>`);
      paramEle.find('.value-zone').append(ele_dom);
      ele = $(`
      <div class="form-group">
        <label for="value">A tuple value</label>
        <input type="text" name="value" id="value" class="form-control value">
      </div>
      `);
      ele.find('input[name="value"]').tagsinput({
        allowDuplicates: true
      });
      tuple_values_zone.append(ele);
    }
    else if (isListType(type.val())) {
      paramEle.find('.value-zone').empty();
      paramEle.find('.value-zone').append(`
    <label for="value">Value</label>
    <input type="text" name="value" id="value" class="form-control value">
    `);
      paramEle.find('.value-zone').find('input').tagsinput({
        allowDuplicates: true
      });
    }
    else {
      paramEle.find('.value-zone').empty();
      paramEle.find('.value-zone').append(`
    <label for="value">Value</label>
    <input type="text" name="value" id="value" class="form-control value" value=${response!=null?response["value"]:""}>
    `);
    }
  });

  if (isUpdate) {
    type.val(isTupleType(response["type"]) ?
      response["type"].split("|")[0] : response["type"]
    ).change();
    if (!isTupleType(response["type"])) {
      if (Array.isArray(response["value"])) {
        let value = paramEle.find('#value');
        value.tagsinput({
          allowDuplicates: true,
        });
        response["value"].forEach(v => {
          value.tagsinput('add', v + "");
        });
      }
    } else {
      tuple_types = response["type"].split("|")[1].split("-");
      paramEle.find('.value-zone').empty();
      tuple_values_zone.empty();
      tuple_types.forEach((types, i) => {
        ele = $(`
          <div class="row">
            <div class="col-12 ">
              <div class="form-group">
                <label for="type">Element Type</label>
                <select name="type" id="subtype" class="form-control">
                  ${getTypesForTuple()}
                </select>
              </div>
            </div>
          </div>`
        );
        ele.find('select').val(types).change();
        paramEle.find('.value-zone').append(ele);
      });
      response["value"].forEach(values => {
        ele = $(`
        <div class="form-group">
          <label for="value">A tuple value</label>
          <input type="text" name="value" id="value" class="form-control value">
        </div>
        `);
        ele.find('input[name="value"]').tagsinput({
          allowDuplicates: true
        });
        tuple_values_zone.append(ele);
        values.forEach(v => {
          ele.find('input[name="value"]').tagsinput('add', v + "");
        });
      });
    }
  }
}
function getDataCmd(divEle, isUpdate = true, obj = null) {
  if (obj == null)
    obj = new Object();
  if (isUpdate)
    obj["newDescription"] = divEle.find('textarea[name="description"]').val();
  else {
    obj["cmd"] = parseInt(divEle.find('input[name="cmd"]').val()) ;
    obj["description"] = divEle.find('textarea[name="description"]').val();
  }
  return obj;
}
function getDataSubCmd(divEle, obj = null) {
  if (obj == null)
    obj = new Object();
  obj["subcmd"] = parseInt(divEle.find('input[name="subcmd"]').val());
  obj["description"] = divEle.find('textarea[name="description"]').val();
  return obj;
}
function getDataParam(divEle, obj = null) {
  if (obj == null)
    obj = new Object();
  obj["description"] = divEle.find('textarea[name="description"]').val();
  obj["type"] = divEle.find('select[name="type"]#type option:selected').val();
  if (isTupleType(obj["type"])) {
    types = divEle.find('select[name="type"]#subtype option:selected').map(function (idx, elem) {
      return $(elem).val();
    }).get();
    values = divEle.find('input[name="value"]').map(function (idx, elem) {
      let tuple_values = getValuesInputDOM($(elem));
      return [tuple_values.map((v, i) => i < types.length ? convertdata(types[i], v) : v)];
    }).get();
    obj["type"] = obj["type"] + "|" + types.join('-');
    obj["value"] = values;
  }
  else {
    obj["value"] = convertdata(obj["type"], getValuesInputDOM(divEle.find('input[name="value"]')));
  }
  return obj;
}
function assert(condition, message) {
  if (!condition) {
    throw message || "Assertion failed";
  }
}

function getTypes() {
  return `
  <option value="byte">byte</option>
  <option value="short">short</option>
  <option value="int">int</option>
  <option value="long">long</option>
  <option value="double">double</option>
  <option value="string8">string8</option>
  <option value="string16">string16</option>
  <option value="string32">string32</option>
  <option value="bytes8">bytes8</option>
  <option value="bytes16">bytes16</option>
  <option value="bytes32">bytes32</option>
  <option value="shorts8">shorts8</option>
  <option value="shorts16">shorts16</option>
  <option value="shorts32">shorts32</option>
  <option value="ints8">ints8</option>
  <option value="ints16">ints16</option>
  <option value="ints32">ints32</option>
  <option value="longs8">longs8</option>
  <option value="longs16">longs16</option>
  <option value="longs32">longs32</option>
  <option value="string8s8">string8s8</option>
  <option value="string8s16">string8s16</option>
  <option value="string8s32">string8s32</option>
  <option value="string16s8">string16s8</option>
  <option value="string16s16">string16s16</option>
  <option value="string16s32">string16s32</option>
  <option value="string32s8">string32s8</option>
  <option value="string32s16">string32s16</option>
  <option value="string32s32">string32s32</option>
  <option value="tuple8">tuple8</option>
  <option value="tuple16">tuple16</option>
  <option value="tuple32">tuple32</option>
  `;
}

function getTypesForTuple() {
  return `
  <option value="byte">byte</option>
  <option value="short">short</option>
  <option value="int">int</option>
  <option value="long">long</option>
  <option value="double">double</option>
  <option value="string8">string8</option>
  <option value="string16">string16</option>
  <option value="string32">string32</option>
  `;

}
function getValuesInputDOM(input_dom) {
  if (input_dom.hasClass("sr-only")) {
    return getValueTagsInput(input_dom);
  }
  else {
    return input_dom.val();
  }
}
function zip(args) {
  var shortest = args.length == 0 ? [] : args.reduce(function (a, b) {
    return a.length < b.length ? a : b
  });

  return shortest.map(function (_, i) {
    return args.map(function (array) { return array[i] })
  });
}
function getValueTagsInput(dom) {
  let parent = $(dom).parent();
  return parent.find('.badge').map((i, e) => $(e).text()).get();
}

$('#styleSelector').append('' +
  '<div class="selector-toggle">' +
  '<a href="javascript:void(0)"></a>' +
  '</div>' + '<div class="col-12 mb-5"><h5 style="text-align: center">Execute Command Notification</h5></div>' + `<div class="notification col-12"></div>` +
  '');
$("#styleSelector .notification").slimScroll({
  setTop: "10px",
  height: "100vh",
  width: "100%"
});
var today = new Date();
var date = today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + today.getDate();
function pushNotification(message, type) {
  let time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
  let dateTime = date + ' ' + time;
  $('#styleSelector .notification').prepend(`
    <div class="row">
      <div class="col-12 alert alert-${type} background-${type}" role="alert">
      <p><b>${dateTime}</b></p>
      <p>${message}</p>
      </div>
    </div>
  `);
}
function changeToSlug(title) {
  let slug;

  //Đổi chữ hoa thành chữ thường
  slug = title.toLowerCase();

  //Đổi ký tự có dấu thành không dấu
  slug = slug.replace(/á|à|ả|ạ|ã|ă|ắ|ằ|ẳ|ẵ|ặ|â|ấ|ầ|ẩ|ẫ|ậ/gi, 'a');
  slug = slug.replace(/é|è|ẻ|ẽ|ẹ|ê|ế|ề|ể|ễ|ệ/gi, 'e');
  slug = slug.replace(/i|í|ì|ỉ|ĩ|ị/gi, 'i');
  slug = slug.replace(/ó|ò|ỏ|õ|ọ|ô|ố|ồ|ổ|ỗ|ộ|ơ|ớ|ờ|ở|ỡ|ợ/gi, 'o');
  slug = slug.replace(/ú|ù|ủ|ũ|ụ|ư|ứ|ừ|ử|ữ|ự/gi, 'u');
  slug = slug.replace(/ý|ỳ|ỷ|ỹ|ỵ/gi, 'y');
  slug = slug.replace(/đ/gi, 'd');
  //Xóa các ký tự đặt biệt
  slug = slug.replace(/\`|\~|\!|\@|\#|\||\$|\%|\^|\&|\*|\(|\)|\+|\=|\,|\.|\/|\?|\>|\<|\'|\"|\:|\;|_/gi, '');
  //Đổi khoảng trắng thành ký tự gạch ngang
  slug = slug.replace(/ /gi, "-");
  //Đổi nhiều ký tự gạch ngang liên tiếp thành 1 ký tự gạch ngang
  //Phòng trường hợp người nhập vào quá nhiều ký tự trắng
  slug = slug.replace(/\-\-\-\-\-/gi, '-');
  slug = slug.replace(/\-\-\-\-/gi, '-');
  slug = slug.replace(/\-\-\-/gi, '-');
  slug = slug.replace(/\-\-/gi, '-');
  //Xóa các ký tự gạch ngang ở đầu và cuối
  slug = '@' + slug + '@';
  slug = slug.replace(/\@\-|\-\@|\@/gi, '');
  //In slug ra textbox có id “slug”
  return slug;
}
