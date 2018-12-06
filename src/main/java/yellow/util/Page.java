package yellow.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import yellow.service.CategoryService;
import yellow.service.impl.CategoryServiceImpl;

public class Page {

	private int endPage;
private int previous;
public int getPrevious() {
	return previous;
}
public void setPrevious(int previous) {
	this.previous = previous;
}

private int page=1;
private int count=5;
private int start=(page-1)*count;
private int next=page+1;
public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
public int getCount() {
	return count;
}
@Override
public String toString() {
	return "Page [endPage=" + endPage + ", previous=" + previous + ", page=" + page + ", count=" + count + ", start="
			+ start + ", next=" + next + "]";
}
public void setCount(int count) {
	this.count = count;
}
public int getPage() {
	return page;
}
public void setPage(int page) {
	this.page = page;
}
public int getStart() {
	return start;
}
public void setStart(int page) {
	if(page>1)
	start = (page-1)*count;
	else
		start =  0 ;
}
public int getNext() {
	return next;
}

public void setNext(int next) {
	this.next = next;
}

}
