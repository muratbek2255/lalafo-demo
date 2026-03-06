package kg.example.muratkanovmuratbeklalafo.service;

import kg.example.muratkanovmuratbeklalafo.model.client.FeedItemDto;
import kg.example.muratkanovmuratbeklalafo.model.client.FeedItemImageDto;
import kg.example.muratkanovmuratbeklalafo.model.view.FeedItemViewModel;
import kg.example.muratkanovmuratbeklalafo.utils.DateUtils;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FeedItemMapperTest {
    private final FeedItemMapper mapper = Mappers.getMapper(FeedItemMapper.class);

    @Test
    void testToViewModel_withImage() {
        FeedItemImageDto imageDto = new FeedItemImageDto();
        imageDto.setImageUrl("http://test.com/img1.png");

        FeedItemDto dto = new FeedItemDto();
        dto.setTitle("Test Title");
        dto.setPrice(100);
        dto.setCity("Bishkek");
        dto.setCreatedAt(1678502400L); // пример unix timestamp
        dto.setImages(List.of(imageDto));

        FeedItemViewModel viewModel = mapper.toViewModel(dto);

        assertEquals("Test Title", viewModel.getTitle());
        assertEquals(100, viewModel.getPrice());
        assertEquals("Bishkek", viewModel.getCity());

        LocalDateTime expectedDate = DateUtils.fromUnixSeconds(1678502400L);
        assertEquals(expectedDate, viewModel.getCreatedAt());

        assertEquals("http://test.com/img1.png", viewModel.getImageUrl());
    }

    @Test
    void testToViewModel_noImages() {
        FeedItemDto dto = new FeedItemDto();
        dto.setTitle("TestItem");
        dto.setImages(List.of());

        FeedItemViewModel viewModel = mapper.toViewModel(dto);

        assertEquals("TestItem", viewModel.getTitle());
        assertNull(viewModel.getImageUrl());
    }

    @Test
    void testToViewModel_nullImages() {
        FeedItemDto dto = new FeedItemDto();
        dto.setTitle("Test item");
        dto.setImages(null);

        FeedItemViewModel viewModel = mapper.toViewModel(dto);

        assertEquals("Test item", viewModel.getTitle());
        assertNull(viewModel.getImageUrl());
    }
}