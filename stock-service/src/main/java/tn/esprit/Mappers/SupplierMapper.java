package tn.esprit.Mappers;

import tn.esprit.Entity.Supplier;
import tn.esprit.dto.SupplierDto;

public class SupplierMapper {
    public static SupplierDto mapToDo(Supplier supplier){
        SupplierDto supplierDto=SupplierDto.builder()
                .name_supplier(supplier.getName_supplier())
                .email(supplier.getEmail())
                .phone(supplier.getPhone())
                .id(supplier.getId())
                .build();
        return supplierDto;
    }
    public static Supplier mapToEntity(SupplierDto supplierDto){
        Supplier supplier=Supplier.builder()
                .name_supplier(supplierDto.getName_supplier())
                .email(supplierDto.getEmail())
                .phone(supplierDto.getPhone())
                .id(supplierDto.getId())
                .build();

        return supplier;
    }
}
