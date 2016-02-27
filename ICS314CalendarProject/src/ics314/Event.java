package iCal;

public class Event {
	private	String version, clas, comment, timezone, pro, id, start, end, stamp, sum;
	private float geograph;

	//constructor
	Event(ver, cla, comnt, tzid, prodid, uid, strt, nd, stmp, sm, geo){
		version = ver;
		class = cla;
		comment = comnt;
		timezone = tzid;
		pro = prodid;
		id = uid;
		start = strt;
		end = nd;
		stamp = stmp;
		sum = sm;
		geograph = geo;
	};

	//accessors
	public String getver() {
		return version;
	}
	public String getcla() {
		return class;
	}
	public String getcomnt() {
		return comment;
	}
	public String gettzid() {
		return timezone;
	}
	public String getprodid() {
		return pro;
	}
	public String getuid() {
		return id;
	}
	public String getstrt() {
		return start;
	}
	public String getnd() {
		return end;
	}
	public String getstmp() {
		return stamp;
	}
	public String getsm() {
		return sum;
	}
	public float getgeo() {
		return geograph;
	}

	//mutators
	void setver(String ver){
		version = ver;
	}
	void setcla(String cla){
		class = cla;
	}
	void setcomnt(String comnt){
		comment = comnt;
	}
	void settzid(String tzid){
		timezone = tzid;
	}
	void setprodid(String prodid){
		pro = prodid;
	}
	void setuid(String uid){
		id = uid;
	}
	void setstrt(String strt){
		start = strt;
	}
	void setnd(String nd){
		end = nd;
	}
	void setstmp(String stmp){
		stamp = stmp;
	}
	void setsm(String sm){
		sum = sm;
	}
	void setgeo(float geo){
		geograph = geo;
	}
}
