package kg.example.muratkanovmuratbeklalafo.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import kg.example.muratkanovmuratbeklalafo.model.view.FeedItemViewModel;
import kg.example.muratkanovmuratbeklalafo.service.FeedService;
import lombok.extern.slf4j.Slf4j;

import java.time.format.DateTimeFormatter;

@Route("test-result")
@PageTitle("Hello, Vaadin!")
@Slf4j
public class MainView extends VerticalLayout {
    private final FeedService feedService;

    public MainView(FeedService feedService) {
        this.feedService = feedService;
        add(buildGrid());
    }

    public Grid<FeedItemViewModel> buildGrid() {
        Grid<FeedItemViewModel> grid = new Grid<>();
        grid.addColumn(new ComponentRenderer<>(item -> {
            String url = item.getImageUrl();

            if (url == null || url.isBlank()) {
                Icon icon = VaadinIcon.PICTURE.create();
                icon.setSize("100px");
                return icon;
            }
            Image image = new Image(item.getImageUrl(), "photo");
            image.setWidth("100px");
            return image;
        })).setHeader("Фото");
        grid.addColumn(FeedItemViewModel::getTitle)
                .setHeader("Название")
                .setAutoWidth(true);
        grid.addColumn(FeedItemViewModel::getPrice)
                .setHeader("Цена");
        grid.addColumn(FeedItemViewModel::getCity)
                .setHeader("Город");
        grid.addColumn(item ->
                item.getCreatedAt().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
        ).setHeader("Дата");

        grid.setItems(feedService.getFeedItems());
        grid.setHeightFull();

        return grid;
    }
}