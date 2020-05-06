package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {
  private TorpedoStore fullMockA;
  private TorpedoStore fullMockB;
  private TorpedoStore emptyMockA;
  private TorpedoStore emptyMockB;
  private GT4500 ship;

  @BeforeEach
  public void init(){
    this.fullMockA = mock(TorpedoStore.class);
    when(fullMockA.fire(1)).thenReturn(true);
    when(fullMockA.isEmpty()).thenReturn(false);

    this.fullMockB = mock(TorpedoStore.class);
    when(fullMockB.fire(1)).thenReturn(true);
    when(fullMockB.isEmpty()).thenReturn(false);

    this.emptyMockA = mock(TorpedoStore.class);
    when(emptyMockA.fire(1)).thenReturn(false);
    when(emptyMockA.isEmpty()).thenReturn(true);

    this.emptyMockB = mock(TorpedoStore.class);
    when(emptyMockB.fire(1)).thenReturn(false);
    when(emptyMockB.isEmpty()).thenReturn(true);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    this.ship = new GT4500(this.fullMockA, this.fullMockB);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(this.fullMockA, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_All_Success(){
    // Arrange
    this.ship = new GT4500(this.fullMockA, this.fullMockB);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(this.fullMockA, times(1)).fire(1);
    verify(this.fullMockB, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Half_Success(){
    // Arrange
    this.ship = new GT4500(this.fullMockA, this.emptyMockB);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(this.fullMockA, times(1)).fire(1);
    verify(this.emptyMockB, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_No_Success(){
    // Arrange
    this.ship = new GT4500(this.emptyMockA, this.emptyMockB);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(this.emptyMockA, times(1)).fire(1);
    verify(this.emptyMockB, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Both_Success(){
    // Arrange
    this.ship = new GT4500(this.fullMockA, this.fullMockB);

    // Act
    boolean firstResult = ship.fireTorpedo(FiringMode.SINGLE);
    boolean secondResult = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, firstResult);
    assertEquals(true, secondResult);
    verify(this.fullMockA, times(1)).fire(1);
    verify(this.fullMockB, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_Half_Success(){
    // Arrange
    this.ship = new GT4500(this.fullMockA, this.emptyMockB);

    // Act
    boolean firstResult = ship.fireTorpedo(FiringMode.SINGLE);
    boolean secondResult = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, firstResult);
    assertEquals(true, secondResult);
    verify(this.fullMockA, times(2)).fire(1);
  }

  @Test
  public void fireTorpedo_Single_No_Success(){
    // Arrange
    this.ship = new GT4500(this.emptyMockA, this.emptyMockB);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
  }
}
