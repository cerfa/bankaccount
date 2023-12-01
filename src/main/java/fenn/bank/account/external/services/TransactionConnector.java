package fenn.bank.account.external.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import fenn.bank.account.dto.Transaction;
import fenn.bank.account.dto.TransactionsHistoryResponse;

@Service
public class TransactionConnector {
	@Value("${connector.transactions.url}")
	private String  transactionsUrl; 
	@Autowired
	private RestTemplate restServiceCaller;

	public List<Transaction> retrieveTrxList(String userId) throws URISyntaxException{
		String trxUrl= transactionsUrl.concat(userId);
		URI uri = new URI(trxUrl);
		ResponseEntity<TransactionsHistoryResponse> result = restServiceCaller.getForEntity(uri, TransactionsHistoryResponse.class);
		return result.getBody() != null ? result.getBody().getTransactionList():new ArrayList<>();	
	}
}
