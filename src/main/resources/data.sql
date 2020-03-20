INSERT INTO t_user (account,pwd) VALUES ('ws','ws'),('zhangyan','zhangyan'),('shenchen','shenchen'),('chenhao','chenhao'),('zhouwei','zhouwei');
INSERT INTO t_game_type (id,name,describe,status) VALUES (1,'聊天室','仅限聊天','active'),(2,'杀人游戏','游戏','active');
INSERT INTO t_scope_role (game_type_id,role_name) VALUES (2,'法官'),(2,'平民'),(2,'警察'),(2,'杀手');