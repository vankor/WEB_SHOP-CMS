package Model;
import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class GoodGuarantieId implements Serializable{
	private GoodItem good;
	private Guarantie guarantie;
	
	@ManyToOne
	public GoodItem getGood() {
		return good;
	}
	public void setGood(GoodItem good) {
		this.good = good;
	}
	
	@ManyToOne
	public Guarantie getGuarantie() {
		return guarantie;
	}
	public void setGuarantie(Guarantie guarantie) {
		this.guarantie = guarantie;
	}
	
}
