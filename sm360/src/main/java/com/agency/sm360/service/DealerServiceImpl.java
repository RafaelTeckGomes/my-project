package com.agency.sm360.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agency.sm360.domain.exception.DealerNotFoundException;
import com.agency.sm360.dto.DealerDto;
import com.agency.sm360.dto.GenericDto;
import com.agency.sm360.entity.Dealer;
import com.agency.sm360.mapper.DealerMapper;
import com.agency.sm360.repository.DealerRepository;

@Service
@Transactional
public class DealerServiceImpl implements DealerService {

	@Autowired
	private DealerRepository dealerRepository;

	@Autowired
	private DealerMapper dealerMapper;

	@Override
	public DealerDto save(DealerDto dto) {
		return (DealerDto) dealerMapper.convertToDto(dealerRepository.save((Dealer) dealerMapper.convertToEntity(dto)));
	}

	@Override
	public void delete(String id) {
		dealerRepository.delete((Dealer) dealerMapper.convertToEntity(new DealerDto(id)));
	}

	@Override
	public void update(DealerDto dto) {
		validateId(dto.getId());
		dealerRepository.save((Dealer) dealerMapper.convertToEntity(dto));
	}

	@Override
	public DealerDto findById(String id) {
		return (DealerDto) dealerMapper.convertToDto(
				dealerRepository.findById(id).orElseThrow(() -> new DealerNotFoundException("DEALER NOT FOUND")));
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DealerDto> findAll() {
		List<Dealer> entityList = (List<Dealer>) dealerRepository.findAll();
		List<? extends GenericDto> dtoList = dealerMapper.convertToDtoList(entityList);
		return (List<DealerDto>) dtoList;
	}

	private void validateId(String id) {
		findById(id);
	}

}
