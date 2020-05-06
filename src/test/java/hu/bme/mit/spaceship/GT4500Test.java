package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {
  private TorpedoStore mockA;
  private TorpedoStore mockB;
  private GT4500 ship;

  @BeforeEach
  public void init(){
    this.mockA = mock(TorpedoStore.class);
    when(mockA.fire(1)).thenReturn(true);
    when(mockA.isEmpty()).thenReturn(false);
    this.mockB = mock(TorpedoStore.class);
    when(mockB.fire(1)).thenReturn(true);
    when(mockB.isEmpty()).thenReturn(false);

    this.ship = new GT4500(mockA, mockB);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(this.mockA, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(this.mockA, times(1)).fire(1);
    verify(this.mockB, times(1)).fire(1);
  }
}
