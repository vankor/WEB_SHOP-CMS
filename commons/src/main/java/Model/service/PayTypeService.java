package Model.service;

import Model.repository.PayTypeDAO;
import Model.entity.PayType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayTypeService extends ServiceImpl<PayType, Integer, PayTypeDAO> {

}
