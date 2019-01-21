package cn.com.gene.comm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询参数类
 * 
 * @author miaoruntu
 *
 */
public class PageQuery implements Serializable {

	private static final long serialVersionUID = -1957315218504265002L;

	// 当前页码
	private int currentpage;

	// 总页数
	private int totalpage;

	// 总记录数
	private int totalcount;

	// 每页显示个数
	private int pagesize;

	// 开始坐标
	private int pagestart;

	// 结束坐标
	private int pageend;
	
	//设置页面不掉
	private List<Integer> cadence = new ArrayList<>();

	public List<Integer> getCadence() {
		return cadence;
	}

	public void setCadence(List<Integer> cadence) {
		this.cadence = cadence;
	}

	/**
	 * 将分布参数传入处理，最终计算出当前页码PageQuery_currPage，开始坐标PageQuery_star，
	 * 结束坐标PageQuery_end，总页数PageQuery_Psize
	 * 
	 * @param infoCount
	 *            记录总数
	 * @param pageSize
	 *            每页显示个数
	 * @param currPage
	 *            当前页码
	 */
	public void setPageParams(int infoCount, int pagesize, int currPage) {
		this.totalcount = infoCount;
		this.pagesize = pagesize;
		this.currentpage = currPage;

		float Psize_l = infoCount / (float) (this.pagesize);
		if (currentpage < 2) {
			currentpage = 1;
			pagestart = 0;
		} else if (currentpage > Psize_l) {
			if (Psize_l == 0) {

				currentpage = 1;
			} else {
				currentpage = (int) Math.ceil(Psize_l);
			}

			pagestart = (currentpage - 1) * this.pagesize;
		} else {
			pagestart = (currentpage - 1) * this.pagesize;
		}
		totalpage = (int) Math.ceil(Psize_l);
		
		//每一个分页7条
		Integer seven = 7;
		Integer yushu = currentpage % seven; 
		if (yushu >0) {
			for (int i=currentpage+1-yushu;i<=currentpage+7-yushu &&i<=totalpage;i++) {
				this.cadence.add(i);
			}
		}else {
			for (int i = currentpage-6;i< currentpage+1;i++) {
				this.cadence.add(i);
			}
			
		}
		
		
		this.pageend = currentpage * this.pagesize;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPagestart() {
		return pagestart;
	}

	public void setPagestart(int pagestart) {
		this.pagestart = pagestart;
	}

	public PageQuery getPageQuery() {
		return this;
	}

	public int getPageend() {
		return pageend;
	}

	public void setPageend(int pageend) {
		this.pageend = pageend;
	}

	public PageQuery() {
		super();
		// TODO Auto-generated constructor stub
	}
}
