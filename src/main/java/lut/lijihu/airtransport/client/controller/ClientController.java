package lut.lijihu.airtransport.client.controller;

import lut.lijihu.airtransport.client.domin.Client;
import lut.lijihu.airtransport.client.invo.ClientAddIn;
import lut.lijihu.airtransport.client.invo.ClientServiceSelectAllIn;
import lut.lijihu.airtransport.client.invo.ClientUpdateIn;
import lut.lijihu.airtransport.client.service.ClientService;
import lut.lijihu.airtransport.core.Message;
import lut.lijihu.airtransport.core.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kj on 2017/1/31.
 */
@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @RequestMapping("/addclient")
    public Message addClient(@RequestBody ClientAddIn clientAddIn){
        Client client=new Client();
        client.setAddress(clientAddIn.getAddress());
        client.setTel(clientAddIn.getTel());
        client.setCode(clientAddIn.getCode());
        client.setName(clientAddIn.getName());
        Message message= clientService.insert(client);
        return message;
    }
    @RequestMapping("/deleteclient")
    public Message deleteClient(@RequestBody String[] ids){
        Message message= clientService.delete(ids);
        return message;
    }
    @RequestMapping("/updateclient")
    public Message updateClient(@RequestBody ClientUpdateIn clientUpdateIn){
        Client client=new Client();
        client.setId(clientUpdateIn.getId());
        client.setName(clientUpdateIn.getName());
        client.setCode(clientUpdateIn.getCode());
        client.setTel(clientUpdateIn.getTel());
        client.setAddress(clientUpdateIn.getAddress());
        Message message=clientService.update(client);
        return message;
    }
    @RequestMapping("/selectall")
    public PageInfo<Client> selectAll(Integer pageNo,Integer pageSize){

        ClientServiceSelectAllIn clientServiceSelectAllIn=new ClientServiceSelectAllIn();
        clientServiceSelectAllIn.setPageNo(pageNo);
        clientServiceSelectAllIn.setPageSize(pageSize);
        PageInfo<Client> clientPageInfo=clientService.selectAll(clientServiceSelectAllIn);
        return clientPageInfo;
    }
}
