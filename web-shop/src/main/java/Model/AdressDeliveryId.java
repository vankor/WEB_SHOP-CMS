package Model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class AdressDeliveryId implements Serializable{
	private DeliveryType delivery;
	private Adress adress;
	
	
	@ManyToOne
	public DeliveryType getDelivery() {
		return delivery;
	}
	public void setDelivery(DeliveryType delivery) {
		this.delivery = delivery;
	}
	
	@ManyToOne
	public Adress getAdress() {
		return adress;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		result = prime * result
				+ ((delivery == null) ? 0 : delivery.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdressDeliveryId other = (AdressDeliveryId) obj;
		if (adress == null) {
			if (other.adress != null)
				return false;
		} else if (!adress.equals(other.adress))
			return false;
		if (delivery == null) {
			if (other.delivery != null)
				return false;
		} else if (!delivery.equals(other.delivery))
			return false;
		return true;
	}
	public void setAdress(Adress adress) {
		this.adress = adress;
	}
}
