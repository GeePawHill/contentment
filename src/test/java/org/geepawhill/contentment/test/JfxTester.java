package org.geepawhill.contentment.test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.geepawhill.contentment.core.Actor;
import org.geepawhill.contentment.core.Context;
import org.geepawhill.contentment.core.Step;
import org.geepawhill.contentment.outline.BasicOutline;
import org.geepawhill.contentment.outline.KvDifference;
import org.geepawhill.contentment.outline.KvMatcher;
import org.geepawhill.contentment.outline.KvOutline;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class JfxTester
{
	private Context context;
	private KvMatcher comparator;

	public JfxTester()
	{
		comparator = new KvMatcher();
	}

	public void waitForPlay(Step step) throws Exception
	{
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		Platform.runLater(() -> {
			step.play(context, () -> {
				countDownLatch.countDown();
			});
		});
		countDownLatch.await();
	}

	public void prepareWindow(Stage stage)
	{
		Pane region = new Pane();
		region.setMaxSize(1600d, 900d);
		region.setMinSize(1600d, 900d);
		Group group = new Group();
		region.getChildren().add(group);
		context = new Context(group);
		stage.setScene(new Scene(region));
		stage.show();
	}

	public void waitForBefore(Step step) throws Exception
	{
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		Platform.runLater(() -> {
			step.before(context);
			countDownLatch.countDown();
		});
		countDownLatch.await();
	}

	public void waitForAfter(Step step) throws Exception
	{
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		Platform.runLater(() -> {
			step.after(context);
			countDownLatch.countDown();
		});
		countDownLatch.await();
	}

	public void beforeSameAsPlayBefore(Actor actor, Step step) throws Exception
	{
//		Snap before = actor.snap();
//		waitForPlay(step);
//		Snap after = actor.snap();
//		assertFalse(before.isEqual(after, false));
//		waitForBefore(step);
//		Snap afterReset = actor.snap();
//		assertTrue(before.isEqual(afterReset, false));
	}

	public void beforeSameAsAfterBefore(Actor actor, Step step) throws Exception
	{
//		Snap before = actor.snap();
//		waitForAfter(step);
//		Snap after = actor.snap();
//		assertFalse(before.isEqual(after, false));
//		waitForBefore(step);
//		Snap afterReset = actor.snap();
//		assertTrue(before.isEqual(afterReset, true));
	}

	public void afterSameAsPlay(Actor actor, Step step) throws Exception
	{
//		Snap before = actor.snap();
//		waitForAfter(step);
//		Snap after = actor.snap();
//		assertFalse(before.isEqual(after, false));
//		waitForBefore(step);
//		waitForPlay(step);
//		Snap afterReset = actor.snap();
//		assertTrue(after.isEqual(afterReset, false));
	}

	public boolean compareSnaps(KvOutline expected, KvOutline actual)
	{
		return compareSnaps(expected, actual, new BasicOutline<KvDifference>());
	}

	public boolean compareSnaps(KvOutline expected, KvOutline actual,
			BasicOutline<KvDifference> details)
	{
		return comparator.match(expected, actual, details);
	}

	public boolean compareSnapsVisual(KvOutline expected, KvOutline actual)
	{
		BasicOutline<KvDifference> details = new BasicOutline<>();
		boolean result = comparator.match(expected, actual, details);
		if (result == true) return true;
		final CountDownLatch countDownLatch = new CountDownLatch(1);
		Platform.runLater(() -> {
			final Stage dialog = new Stage();
			dialog.initModality(Modality.APPLICATION_MODAL);
			AnchorPane pane = new AnchorPane();
			List<KvDifference> messages = details.asLeafList();
			ObservableList<KvDifference> observable = FXCollections.observableList(messages);
			FXCollections.sort(observable,(left,right) -> left.getKey().compareTo(right.getKey()));
			TableView<KvDifference> detailsView = new TableView<>(observable);
			detailsView.setStyle("-fx-font: 18px Tahoma");

			TableColumn<KvDifference, String> keyColumn = new TableColumn<>("Key");
			keyColumn.setPrefWidth(400d);
			keyColumn.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getKey()));
			detailsView.getColumns().add(keyColumn);
			
			TableColumn<KvDifference, String> expectedColumn = new TableColumn<>("Expected");
			expectedColumn.setPrefWidth(250d);
			expectedColumn.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getExpected()));
			detailsView.getColumns().add(expectedColumn);

			TableColumn<KvDifference, String> actualColumn = new TableColumn<>("Actual");
			actualColumn.setPrefWidth(250d);
			actualColumn.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getActual()));
			detailsView.getColumns().add(actualColumn);
			
			AnchorPane.setBottomAnchor(detailsView, 0d);
			AnchorPane.setTopAnchor(detailsView, 0d);
			AnchorPane.setLeftAnchor(detailsView, 0d);
			AnchorPane.setRightAnchor(detailsView, 0d);
			pane.getChildren().add(detailsView);

			dialog.setOnCloseRequest((event) -> countDownLatch.countDown());
			Scene scene = new Scene(pane, 1200d, 800d);
			dialog.setScene(scene);
			dialog.show();
		});
		try
		{
			countDownLatch.await();
		}
		catch (InterruptedException ignored)
		{
		}
		return false;
	}
}