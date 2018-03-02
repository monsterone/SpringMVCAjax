package online.shixun.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import online.shixun.project.dto.City;
import online.shixun.project.dto.Distrct;
import online.shixun.project.dto.Province;

@Controller
@RequestMapping(value = "/select")
public class SelectRegionController {
	private String provinceCode;
	// 省份数据
	private static List<Province> provinceList = new ArrayList<Province>();
	// 城市数据
	private static Map<String, List<City>> cityMap = new HashMap<>();
	// 区域数据
	private static Map<String, List<Distrct>> distrctMap = new HashMap<>();
	static {
		provinceList.add(new Province("hubei", "湖北省"));
		provinceList.add(new Province("jilin", "吉林省"));

		List<City> cities1 = new ArrayList<>();
		cities1.add(new City("wuhan", "武汉市"));
		cities1.add(new City("suizhou", "随州市"));
		cityMap.put("hubei", cities1);

		List<City> cities2 = new ArrayList<>();
		cities2.add(new City("changchun", "长春市"));
		cities2.add(new City("jilin", "吉林市"));
		cityMap.put("jilin", cities2);

		List<Distrct> distrct1 = new ArrayList<>();
		distrct1.add(new Distrct("hongshan", "洪山区"));
		distrct1.add(new Distrct("jiangxia", "江夏区"));
		distrctMap.put("wuhan", distrct1);

		List<Distrct> distrct2 = new ArrayList<>();
		distrct2.add(new Distrct("zengdu", "曾都区"));
		distrct2.add(new Distrct("guangshui", "广水区"));
		distrctMap.put("suizhou", distrct2);

		List<Distrct> distrct3 = new ArrayList<>();
		distrct3.add(new Distrct("chaoyang", "朝阳区"));
		distrct3.add(new Distrct("nanguan", "南关区"));
		distrctMap.put("changchun", distrct3);

		List<Distrct> distrct4 = new ArrayList<>();
		distrct4.add(new Distrct("chuanying", "船营区"));
		distrct4.add(new Distrct("longtan", "龙潭区"));
		distrctMap.put("jilin", distrct4);
	}

	@RequestMapping("/")
	public String selectPage() {
		return "select";
	}

	@ResponseBody
	@RequestMapping(value = "province_ajax")
	public String selectProvine() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("provinceList", provinceList);
		return jsonObject.toJSONString();
	}

	@ResponseBody
	@RequestMapping(value = "city_ajax", method = RequestMethod.POST)
	public String selectCity(String name) {
		JSONObject jsonObject = new JSONObject();
		String code = null;
		for (int i = 0; i < provinceList.size(); i++) {
			if (provinceList.get(i).getName().equals(name)) {
				code = provinceList.get(i).getCode();
			}
		}
		List<City> city = cityMap.get(code);
		jsonObject.put("cityMap", city);
		return jsonObject.toJSONString();
	}

	@ResponseBody
	@RequestMapping(value="distrct_ajax",method=RequestMethod.POST)
	public String selectDistrct(String name){
		JSONObject jsonObject=new JSONObject();
		String code=null;
		for(Map.Entry<String, List<City>> entry : cityMap.entrySet()){
			//根据名字得到相应的code
			List<City> city=entry.getValue();
			for(int i=0;i<city.size();i++){
				if(city.get(i).getName().equals(name)){
					code=city.get(i).getCode();
				}
			}
		}
		List<Distrct> distrct=distrctMap.get(code);
		jsonObject.put("distrctMap",distrct);
		return jsonObject.toJSONString();
	}
}
