package lut.lijihu.airtransport.good.service;

import lut.lijihu.airtransport.good.domin.Good;

/**
 * Created by kj on 2017/2/8.
 */
public interface GoodService {
    void addGood(Good good);
    void deleteGood(Good good);
    void updateGood(Good good);
    Good selectById(String id);
}
