package kh.spring.s02.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import kh.spring.s02.board.model.service.BoardService;
import kh.spring.s02.board.model.vo.BoardVo;
import kh.spring.s02.common.file.FileUtil;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	private final static int BOARD_LIMIT = 5; 
	private final static int PAGE_LIMIT = 3;
	private final static String UPLOARD_FOLDER = "\\resources\\uploadfiles" ;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView viewListBoard( ModelAndView mv) {
		
		
		// TODO
		// 검색 단어는 제목, 내용, 작성자에서 포함되어 있으면 찾기
		// null 또는 "" 은 검색하지 않음
//		String searchWord = "";
//		String searchWord = null;
		String searchWord = "답";
		
		// TODO
		int currentPage = 2;
		int totalCnt = service.selectOneCount(searchWord);
		int totalPage = (totalCnt%BOARD_LIMIT==0)?
				(totalCnt/BOARD_LIMIT) : 
				(totalCnt/BOARD_LIMIT) + 1;
		int startPage = (currentPage%PAGE_LIMIT==0) ?
				(currentPage/PAGE_LIMIT -1)*PAGE_LIMIT + 1 :
				(currentPage/PAGE_LIMIT   )*PAGE_LIMIT + 1;
		int endPage = (startPage + PAGE_LIMIT > totalPage) ?
				totalPage : 
				(startPage + PAGE_LIMIT);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("totalPage", totalPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("currentPage", currentPage);
		mv.addObject("pageInfo", map);
		
//		mv.addObject("totalPage", totalPage);
//		mv.addObject("startPage", startPage);
//		mv.addObject("endPage", endPage);
//		mv.addObject("currentPage", currentPage);
		
		mv.addObject("boardlist", service.selectList(currentPage, BOARD_LIMIT, searchWord));
		mv.setViewName("board/list");
		return mv;
	}
	
	@GetMapping("/update")
	public void viewUpdateBoard() {
		
	}
//	@PostMapping("/update")
	@GetMapping("/updatePostTest")
	public void updateBoard() {
		// TODO
		int boardNum = 1;
		String boardTitle = "수정제목";
		String boardContent = "수정내용";
		String boardOriginalFilename = "";  // "" 파일없음
		String boardRenameFilename = "";  // "" 파일없음
		
		BoardVo vo = new BoardVo();
		vo.setBoardTitle(boardTitle);
		vo.setBoardContent(boardContent);
		vo.setBoardOriginalFilename(boardOriginalFilename);
		vo.setBoardRenameFilename(boardRenameFilename);
		int result = service.update(vo);
		
	}
	
	
	@GetMapping("/delete")
	public void viewDeleteBoard() {
		//TODO
		int boardNum = 10;
		int result = service.delete(boardNum);
	}
	
	
	// 글 상세 읽기 화면 
	@GetMapping("/read")
	public ModelAndView viewReadBoard(
			ModelAndView mv,
			@RequestParam("boardNum") int boardNum
			) {
		//TODO
		String writer = "user22";
		BoardVo vo = service.selectOne(boardNum, writer);
		mv.addObject("board", vo);
		
		List<BoardVo> replyList = service.selectReplyList(boardNum);
		mv.addObject("replyList", replyList);
		
		mv.setViewName("board/read");
		return mv;
		
	}
	
	// 원글 작성페이지 이동
	@GetMapping("/insert")
	public ModelAndView viewInsertBoard(
			ModelAndView mv
			, HttpServletRequest req
			, HttpSession session
			, BoardVo vo
			) {
		mv.setViewName("board/insert");
		return mv;
	}
	
	// 원글 작성 
	@PostMapping("/insert")
	public ModelAndView doInsertBoard(
			MultipartHttpServletRequest multiReq,
			@RequestParam(name = "report", required = false) MultipartFile multi
			, HttpServletRequest request
			,ModelAndView mv
			, BoardVo vo
			) {
		Map<String, String> filePath;
		List<Map<String, String>> fileListPath;
		try {
			fileListPath = new FileUtil().saveFileList(multiReq, request, null);
			filePath = new FileUtil().saveFile(multi, request, null);
			vo.setBoardOriginalFilename(filePath.get("original"));
			vo.setBoardRenameFilename(filePath.get("rename"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		vo.setBoardWriter("user22");  //TODO
		int result = service.insert(vo);
		return mv;
	}
	
	// 답글작성 페이지이동
	@GetMapping("/insertReply")
	public ModelAndView viewInsertReply(ModelAndView mv
			, int boardNum // 몇번글에 답글인지 
			) {
		mv.setViewName("insertReply");
		return mv;
	}
	
	
	
	// 답글작성
//	TODO
//	@PostMapping("/insertReply")
	@GetMapping("/insertReplyPostTest")
	public ModelAndView viewInsertReply(ModelAndView mv
			, BoardVo vo
		) {
		int boardNum = 6;
		vo.setBoardNum(boardNum);
		
		vo.setBoardContent("임시6답내용");
		vo.setBoardTitle("임시6답제목");
		vo.setBoardWriter("user22");
		
		service.insert(vo);
		
		return mv;
	}
	
	@PostMapping("/insertReplyAjax")
	@ResponseBody
	public String insertReplyAjax(BoardVo vo) {
		
		
		System.out.println("######");
		System.out.println(vo);
		
		
		vo.setBoardWriter("user22");
		
		// 답글 작성 
		service.insert(vo);
		
		// 연관 답글들 조회해서 ajax 로 return 해야함.
		List<BoardVo> replyList = service.selectReplyList(vo.getBoardNum());
		//ajax 는 mv 에 실어갈 수 없음. mv.addObject("replyList", replyList);
		
		
		return new Gson().toJson(replyList);
	}
	
	
	
	
//	@RequestMapping(value = "/boardinsert")
	@RequestMapping("/test")
	public ModelAndView test(ModelAndView mv) {

		return mv;
	}
	
	
	
	//@ExceptionHandler
	
}
