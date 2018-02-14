package HackMaster.presentation;

import HackMaster.objects.CardClass;
import HackMaster.objects.PlayerClass;

public interface DrawToScreen {
    void DrawCard(CardClass card, int slot);
    void drawPlayerResource(PlayerClass player);
    void drawPlayedCard(CardClass card);
}
