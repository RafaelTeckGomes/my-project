package com.agency.sm360.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agency.sm360.domain.exception.TierLimitNotFoundException;
import com.agency.sm360.dto.GenericDto;
import com.agency.sm360.dto.TierLimitDto;
import com.agency.sm360.entity.TierLimit;
import com.agency.sm360.mapper.TierLimitMapper;
import com.agency.sm360.repository.TierLimitRepository;

@Service
@Transactional
public class TierLimitServiceImpl implements TierLimitService {

	@Autowired
	private TierLimitRepository tierLimitRepository;

	@Autowired
	private TierLimitMapper tierLimitMapper;

	@Override
	public void update(TierLimitDto dto) {
		validateId(dto.getId());
		tierLimitRepository.save((TierLimit) tierLimitMapper.convertToEntity(dto));

	}

	@Override
	public TierLimitDto findById(String id) {
		return (TierLimitDto) tierLimitMapper
				.convertToDto(tierLimitRepository.findById(id).orElseThrow(() -> new TierLimitNotFoundException(id)));
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TierLimitDto> findAll() {
		List<TierLimit> entityList = (List<TierLimit>) tierLimitRepository.findAll();
		List<? extends GenericDto> dtoList = tierLimitMapper.convertToDtoList(entityList);
		return (List<TierLimitDto>) dtoList;
	}

	private void validateId(String id) throws TierLimitNotFoundException {
		findById(id);
	}

}
