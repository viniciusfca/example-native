package com.examplenative.business;

import java.net.http.HttpResponse;
import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.examplenative.dto.CepDTO;
import com.examplenative.errors.ErrorBusiness;
import com.examplenative.errors.ErrorNotFound;
import com.examplenative.model.Cep;
import com.examplenative.model.CepCache;
import com.examplenative.repository.CepCacheRepository;
import com.examplenative.repository.CepRepository;
import com.examplenative.util.HttpClientUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CepBusiness {

	private HttpClientUtils httpUtil;
	private CepRepository cepRepository;
	private CepCacheRepository cacheRepository;

	@Autowired
	private ModelMapper modelMapper;

	private String url = "https://viacep.com.br/ws/%s/json/";


	public ResponseEntity<CepDTO> findByCep(String cep) throws JsonMappingException, JsonProcessingException {

		HttpResponse<String> response = null;
		var objectMapper = new ObjectMapper();
		var cepCache = cacheRepository.findByCep(cep);

		if (cepCache != null) {

			return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(cepCache, CepDTO.class));

		} else {
			
			var cepOpt = cepRepository.findByCep(cep);
			
			if (cepOpt.isPresent()) {
				
				cepCache = modelMapper.map(cepOpt.get(), CepCache.class);
				cepCache.setTtl(1L);

				cacheRepository.save(cepCache);
				return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(cepOpt.get(), CepDTO.class));

			}
			
			response = httpUtil.restCall(String.format(url, cep), "", "", "", HttpMethod.GET);

			if (response != null && response.statusCode() == 200) {

				var body = objectMapper.readValue(response.body(), HashMap.class);

				if (body.get("erro") != null && (boolean) body.get("erro")) {
					throw new ErrorNotFound(String.format("Não foi possível encontrar o CEP %s", cep));
				} else {

					var cepModel = objectMapper.readValue(response.body(), Cep.class);

					cepModel.setCep(cepModel.cep.replace("-", ""));
					cepModel = cepRepository.save(cepModel);
					
					cepCache = modelMapper.map(cepModel, CepCache.class);
					cepCache.setTtl(1L);

					cacheRepository.save(cepCache);

					return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(cepModel, CepDTO.class));
				}
			} else {
				throw new ErrorBusiness("O cep %s é inválido");
			}
		}

	}

	@Autowired
	public void setCepRepository(CepRepository cepRepository) {
		this.cepRepository = cepRepository;
	}

	@Autowired
	public void setHttpUtil(HttpClientUtils httpUtil) {
		this.httpUtil = httpUtil;
	}

	@Autowired
	public void setCacheRepository(CepCacheRepository cacheRepository) {
		this.cacheRepository = cacheRepository;
	}

}
