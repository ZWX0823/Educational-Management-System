var depts = new Object();
depts['01'] = new Array('0101','英语','0102','日语','0103','法语','0104','对外汉语','0105','翻译');
depts['02'] = new Array('0201','大气科学','0202','大气科学（气候学方向）','0203','大气科学（气候资源方向）');
depts['03'] = new Array('0301','地理信息科学','0302','人文地理与城市规划','0303','自然地理与资源环境');
depts['04'] = new Array('0401','海洋技术','0402','海洋科学');
depts['05'] = new Array('0501','电子科学与技术','0502','电子信息工程','0503','通信工程','0504','信息工程');
depts['06'] = new Array('0601','材料物理','0602','物理学','0603','应用物理学');
depts['07'] = new Array('0701','动画','0702','数字媒体艺术','0703','艺术与科技');
depts['08'] = new Array('0801','财务管理','0802','工商管理','0803','国际经济与贸易','0804','会计学','0805','人力资源');
depts['09'] = new Array('0901','法学','0902','公共事业管理','0903','行政管理');
depts['10'] = new Array('1001','数学与应用数学','1002','统计学','1003','应用统计学');
depts['11'] = new Array('1101','马克思主义');
depts['12'] = new Array('1201','体育');
depts['13'] = new Array('1301','软件工程','1302','网络工程','1303','计算机科学与技术','1304','网络安全');

function set_specialty(dept,specialty) {
    var de,sp;
    var i,ii;
    de = dept.value;
    sp = specialty.value;

    specialty.length = 1;
    if (de=='')return;
    if (typeof (depts[de])=='undefined')return;
    for (i = 0,ii = 1;i < depts[de].length;i=i+2){
        specialty.options[ii] = new Option();
        specialty.options[ii].value = depts[de][i];
        specialty.options[ii].text = depts[de][i+1];
        ii = ii +1;
    }
}