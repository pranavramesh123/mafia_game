/*
 *  Copyright (C) 2015 mafiagame.ir
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package co.mafiagame.bot.handler;

import co.mafiagame.bot.AccountCache;
import co.mafiagame.bot.CommandDispatcher;
import co.mafiagame.bot.GameContainer;
import co.mafiagame.bot.TelegramClient;
import co.mafiagame.bot.telegram.TCallBackQuery;
import co.mafiagame.bot.telegram.TMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author Esa Hekmatizadeh
 */
public abstract class TelegramCallbackHandler {
    @Autowired
    private CommandDispatcher commandDispatcher;
    @Autowired
    protected TelegramClient client;
    @Autowired
    protected AccountCache accountCache;
    @Autowired
    protected GameContainer gameContainer;

    @PostConstruct
    protected final void init() {
        commandDispatcher.registerCallbackHandler(getCommandString(), this);
    }

    protected abstract String getCommandString();

    public abstract void execute(TCallBackQuery callBackQuery);

}
