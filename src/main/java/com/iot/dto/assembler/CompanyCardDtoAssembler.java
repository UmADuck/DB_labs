package com.iot.dto.assembler;


import com.iot.controller.CompanyCardController;
import com.iot.domain.CompanyCard;
import com.iot.dto.CompanyCardDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class CompanyCardDtoAssembler implements RepresentationModelAssembler<CompanyCard, CompanyCardDto> {
    @Override
    public CompanyCardDto toModel(CompanyCard entity) {
        CompanyCardDto companyCardDto = CompanyCardDto.builder()
                .id(entity.getId())
                .companyName(entity.getCompanyName())
                .build();
        Link selfLink = linkTo(methodOn(CompanyCardController.class).getBook(companyCardDto.getId())).withSelfRel();
        companyCardDto.add(selfLink);
        return companyCardDto;
    }

    @Override
    public CollectionModel<CompanyCardDto> toCollectionModel(Iterable<? extends CompanyCard> entities) {
        CollectionModel<CompanyCardDto> companyCardDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CompanyCardController.class).getAllBooks()).withSelfRel();
        companyCardDtos.add(selfLink);
        return companyCardDtos;
    }

    public CollectionModel<CompanyCardDto> toCollectionModel(Iterable<? extends CompanyCard> entities, Link link) {
        CollectionModel<CompanyCardDto> companyCardDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        companyCardDtos.add(link);
        return companyCardDtos;
    }
}
