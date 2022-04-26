package pack;

public class BusinessImpl implements BusinessInter{
	private SangpumInter SangpumInter;
	
	public void setSangpumInter(SangpumInter sangpumInter) {
		SangpumInter = sangpumInter;
	}
	
	@Override
	public void dataList() {
		for(SangpumDto s:SangpumInter.selectList())
		System.out.println(s.getCode() + " " + s.getSang() + " " + s.getSu() + " " + s.getDan());
	}
}
