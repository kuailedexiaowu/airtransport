package lut.lijihu.airtransport.client.service;


import lut.lijihu.airtransport.client.domin.Client;
import lut.lijihu.airtransport.client.invo.ClientServiceSelectAllIn;
import lut.lijihu.airtransport.core.Message;
import lut.lijihu.airtransport.core.PageInfo;

/**
 * Created by kj on 2017/1/31.
 */
public interface ClientService {
     Message delete(String[] ids);
     Message update(Client client);
     Message insert(Client client);
     PageInfo<Client> selectAll(ClientServiceSelectAllIn clientServiceSelectAllIn);
     Client findById(String id);

}
