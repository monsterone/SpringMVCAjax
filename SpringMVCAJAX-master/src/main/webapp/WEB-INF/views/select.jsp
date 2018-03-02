<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择页面</title>
<script type="text/javascript" src="../plugins/jquery-3.2.1.min.js"></script>
</head>
<body>
	<select id="provinceSelector" >
		<option>请选择省份</option>
	</select>
	<select id="citySelector">
		<option>请选择城市</option>
	</select>
	<select id="distrctSelector">
		<option>请选择城区</option>
	</select>
	<script type="text/javascript">
		function initProvince() {
			var opt = {
				url : "province_ajax",
				data : {},
				dataType : "json",
				scriptCharset : 'utf-8',
				success : function(json) {
					var i = 0;
					for (i in json.provinceList) {
						$("#provinceSelector").append(
								"<option value="+json.provinceList[i].name+">"
										+ json.provinceList[i].name
										+ "</option>");
					}
				}
			}
			$.ajax(opt);
		}
		function selectProvince(provinceCode) {
			var opt1 = {
					url : "city_ajax",
					data :{"name": provinceCode},
					type : 'post',
					dataType : "json",
					scriptCharset : 'utf-8',
					success : function(json) {
						var i = 0;
						var list = json.cityMap;
						$("#citySelector").append("<option>请选择城市</option>")
						for(i in list){
							$("#citySelector").append(
									"<option value="+list[i].name+">"
											+ list[i].name
											+ "</option>");
						}
					}
				}
			$.ajax(opt1);
		}
		function selectCity(cityCode) {
			var opt2 = {
					url : "distrct_ajax",
					data :{"name": cityCode},
					type : 'post',
					dataType : "json",
					scriptCharset : 'utf-8',
					success : function(json) {
						var i = 0;
						var list = json.distrctMap;
						$("#distrctSelector").append("<option>请选择城区</option>")
						for(i in list){
							$("#distrctSelector").append(
									"<option value="+list[i].name+">"
											+ list[i].name
											+ "</option>");
						}
					}
				}
			$.ajax(opt2);
		}
		$(document).ready(function() {
			//初始化省的数据
			initProvince();
			//选择省
			$("#provinceSelector").change(function(e) {
				$("#citySelector").html("");
				//获取选中的省的编号
				var provinceCode = $(this).find("option:selected").val();
				selectProvince(provinceCode);
			});
			//选择市
			$("#citySelector").change(function(e) {
				$("#distrctSelector").html("");
				var cityCode = $(this).find("option:selected").val();
				selectCity(cityCode);
			});
		});
	</script>
</body>
</html>