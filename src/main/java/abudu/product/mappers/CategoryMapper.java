package abudu.product.mappers;


import abudu.product.dto.CategoryDTO;
import abudu.product.models.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
   public CategoryDTO mapToDTO(Category category){
       if(category == null){
           return null;
       }
       CategoryDTO categoryDTO = new CategoryDTO();
       categoryDTO.setId(category.getId());
       categoryDTO.setName(category.getName());
       return categoryDTO;
   }

   public Category mapToEntity(CategoryDTO categoryDTO){
       if(categoryDTO == null){
           return null;
       }
       Category category = new Category();
       category.setId(categoryDTO.getId());
       category.setName(categoryDTO.getName());
       return category;
   }
}
