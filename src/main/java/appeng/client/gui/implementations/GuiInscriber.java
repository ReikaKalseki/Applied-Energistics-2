/*
 * This file is part of Applied Energistics 2.
 * Copyright (c) 2013 - 2014, AlgorithmX2, All rights reserved.
 *
 * Applied Energistics 2 is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Applied Energistics 2 is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Applied Energistics 2.  If not, see <http://www.gnu.org/licenses/lgpl>.
 */

package appeng.client.gui.implementations;

import net.minecraft.entity.player.InventoryPlayer;
import appeng.client.gui.AEBaseGui;
import appeng.client.gui.widgets.GuiProgressBar;
import appeng.client.gui.widgets.GuiProgressBar.Direction;
import appeng.container.implementations.ContainerInscriber;
import appeng.container.implementations.ContainerUpgradeable;
import appeng.core.localization.GuiText;
import appeng.tile.misc.TileInscriber;

public class GuiInscriber extends AEBaseGui
{

	final ContainerInscriber cvc;
	GuiProgressBar pb;

	public GuiInscriber(InventoryPlayer inventoryPlayer, TileInscriber te)
	{
		super( new ContainerInscriber( inventoryPlayer, te ) );
		cvc = (ContainerInscriber) inventorySlots;
		this.ySize = 176;
		this.xSize = hasToolbox() ? 246 : 211;
	}

	@Override
	public void initGui()
	{
		super.initGui();

		pb = new GuiProgressBar(  cvc,  "guis/inscriber.png", 135, 39, 135, 177, 6, 18, Direction.VERTICAL );
		this.buttonList.add( pb );
	}

	@Override
	public void drawBG(int offsetX, int offsetY, int mouseX, int mouseY)
	{
		bindTexture( "guis/inscriber.png" );
		pb.xPosition = 135 + guiLeft;
		pb.yPosition = 39 + guiTop;

		this.drawTexturedModalRect( offsetX, offsetY, 0, 0, 211 - 34, ySize );
		
		if ( drawUpgrades() )
			this.drawTexturedModalRect( offsetX + 177, offsetY, 177, 0, 35, 14 + cvc.availableUpgrades() * 18 );
		if ( hasToolbox() )
			this.drawTexturedModalRect( offsetX + 178, offsetY + ySize - 90, 178, ySize - 90, 68, 68 );
	}

	@Override
	public void drawFG(int offsetX, int offsetY, int mouseX, int mouseY)
	{
		pb.setFullMsg( cvc.getCurrentProgress() * 100 / cvc.getMaxProgress() + "%" );

		fontRendererObj.drawString( getGuiDisplayName( GuiText.Inscriber.getLocal() ), 8, 6, 4210752 );
		fontRendererObj.drawString( GuiText.inventory.getLocal(), 8, ySize - 96 + 3, 4210752 );
	}

	protected boolean drawUpgrades()
	{
		return true;
	}

	protected boolean hasToolbox()
	{
		return ((ContainerUpgradeable) inventorySlots).hasToolbox();
	}

}
