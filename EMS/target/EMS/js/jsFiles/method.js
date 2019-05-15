window.onload = function () {

    //设置一个院系的公共下标
    var dIndex = -1;
    var sIndex = -1;
    var deptEle = $("#dept");
    var speEle = $("#specialty");
    var yearEle = $("#year");
    var classEle = $("#class");
    //先设置院系的值
    for(var i = 0;i < dept.length;i++){
        var op = new Option(dept[i],i);
        deptEle.options.add(op);
    }
    function change(obj) {
        if (obj.value == -1){
            speEle.options.length = 0;
            yearEle.options.length = 0;
            classEle.options.length = 0;
        }
        //获取值
        var val = obj.value;
        dIndex = obj.value;
        //获取专业
        var spe = dept[dIndex].specialty;
        //获取年级
        var year = spe.year;
        //获取默认班级
        var c = year.class[0];
        //先清空专业 年级 班级
        speEle.options.length = 0;
        yearEle.options.length = 0;
        classEle.options.length = 0;
        for (var i = 0;i < spe.length;i++){
            var op = new Option(spe[i],i);
            speEle.options.add(op);
        }
        for (var i = 0;i < year.length;i++){
            var op = new Option(year[i],i);
            yearEle.options.add(op);
        }
        for (var i = 0;i < c.length;i++){
            var op = new Option(c[i],i);
            classEle.options.add(op);
        }
    }
    function change2(obj) {
        var val = obj.selectedIndex;
        sIndex = obj.selectedIndex;
        var year = dept[dIndex].specialty[val].year;
        yearEle.options.length = 0;
        classEle.options.length = 0;
        for (var i = 0;i < year.length;i++){
            var op = new Option(year[i],i);
            yearEle.options.add(op);
        }
    }
    function change3(obj) {
        var val = obj.selectedIndex;
        var c = dept[dIndex].specialty[sIndex].year[val].class;
        classEle.options.length = 0;
        for (var i = 0;i < c.length;i++){
            var op = new Option(c[i],i);
            classEle.options.add(op);
        }
    }
    deptEle.onchange = function () {
        change(this)
    }
    speEle.onchange = function () {
        change2(this)
    }
    yearEle.onchange = function () {
        change3(this)
    }
}